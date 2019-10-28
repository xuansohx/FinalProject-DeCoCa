import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class CarClient implements SerialPortEventListener {
	Socket socket;
	boolean rflag = true;
	private SerialPort serialPort;
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;
	private BufferedInputStream bin;
	private InputStream in;
	private OutputStream out;
	static String str;
	static CarClient st;
	static CarClient client;
	String checkData;
	static String[] status = new String[13];

	public CarClient() {

	}

	public CarClient(String ip, int port) throws IOException {
		boolean flag = true;
		while (flag) {
			try {
				socket = new Socket(ip, port);
				if (socket != null && socket.isConnected()) {
					break;
				}
			} catch (Exception e) {
				System.out.println("Re-Try");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		new Receiver(socket).start();
	}

	public String statustoString() {
		String str = "";
		for (int i = 1; i < 13; i++) {
			str += status[i];
		}
		return str;
	}

	public void sendMsg(String msg) throws IOException {
		Sender sender = null;
		sender = new Sender(socket);
		sender.setMsg(msg);
		sender.start();
	}

	public void start() throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean sflag = true;
		while (sflag) {
			System.out.println("input msg");
			String str = sc.next();
			sendMsg(str);
			if (str.equals("q")) {
				rflag = false;
				break;
			}
		}
		System.out.println("Bye....");
		sc.close();
	}

	class Sender extends Thread {
		OutputStream out;
		DataOutputStream dout;
		String msg;

		public Sender(Socket socket) throws IOException {
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public void run() {
			if (dout != null) {
				try {
					dout.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Receiver extends Thread {
		Socket socket;
		InputStream in;
		DataInputStream din;

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);
		}

		public void run() {
			try {
				while (rflag) {
					str = din.readUTF();
					System.out.println(str);
					String[] spl = str.split("-");
					if (str.equals("1")) { // 정보를 cluster 한테 보낸다.
						System.out.println(statustoString());
						sendMsg(statustoString());
					} else if (str.equals("2") || str.equals("door")) { // 문 열고 닫기
						if (status[5] != null) {
							if (status[5].equals("1")) // 열려있다면
								status[5] = "0"; // 잠궈라
							else if (status[5].equals("0")) // 닫혀있으면
								status[5] = "1"; // 열어라
							st.sendData("W28" + statustoString());
						}
					} else if (str.charAt(0) == 't') { // 온도 변경
						String tmp = str.substring(1);
						status[4] = tmp;
						st.sendData("W28" + statustoString());
					}else if (str.equals("engine")) { // 시동 끄기 / 켜기
						if(status[8].equals("1")){
							status[8]="0";
						}
						else if(status[8].equals("0")){
							status[8]="1";
						}
						st.sendData("W28"+ statustoString());					
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean checkSerialData(String data) {
		boolean check = false;
		// :U2800000050000000000000002046
		checkData = data.substring(1, 28);
		String checkSum = data.substring(28, 30);
		if (data.charAt(1) == 'U') {
			for (int i = 1; i <= 2; i++) {
				status[i] = data.substring(i * 3 + 1, i * 3 + 4);
			}
			for (int i = 1; i <= 2; i++) {
				status[i + 2] = data.substring(i * 2 + 8, i * 2 + 10);
			}
			for (int i = 1; i <= 4; i++) {
				status[i + 4] = data.substring(i + 13, i + 1 + 13);
			}
			for (int i = 1; i <= 2; i++) {
				status[i + 8] = data.substring(i * 3 + 15, i * 3 + 18);
			}
			for (int i = 1; i <= 2; i++) {
				status[i + 10] = data.substring(i * 2 + 22, i * 2 + 24);
			}
		}
		char c[] = checkData.toCharArray();
		int cdata = 0;
		for (char cc : c) {
			cdata += cc;
		}
		cdata = (cdata & 0xFF);
		String serialCheckSum = Integer.toHexString(cdata).toUpperCase();
		if (serialCheckSum.trim().equals(checkSum)) {
			check = true;
		}
		return check;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[128];
			try {
				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer);
				}
				String ss = new String(readBuffer);
				boolean result = checkSerialData(ss);
				System.out.println("Result:" + result);
				System.out.println("Receive Low Data:" + ss + "||");

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void sendData(String data) {
		SerialWriter sw = new SerialWriter(data);
		new Thread(sw).start();
	}

	public void connectSerial() throws Exception {

		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use"); // 그래도문제가 있으면 하지마라, 다른 사람이 쓰고 있다.
		} else {
			commPort = portIdentifier.open(this.getClass().getName(), 5000);
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort; // COMMPORT
				serialPort.addEventListener(this); //
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // 통신속도
						SerialPort.DATABITS_8, // 데이터 비트
						SerialPort.STOPBITS_1, // stop 비트
						SerialPort.PARITY_NONE); // 패리티 우리가 전송하는건 검증 하겠다 .
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	private class SerialWriter implements Runnable {
		String data;

		public SerialWriter() {
			this.data = ":G11A9\r"; // 나도 같이 참가 하겠습니다 .
		}

		public SerialWriter(String serialData) {
			// W28 00000000 000000000000
			// :W28 00000000 000000000000 53 \r : \r 시작과 끝 53 체크섬
			String sdata = sendDataFormat(serialData);
			System.out.println(sdata);
			this.data = sdata;
		}

		public String sendDataFormat(String serialData) {
			serialData = serialData.toUpperCase();
			char c[] = serialData.toCharArray();
			int cdata = 0;
			for (char cc : c) {
				cdata += cc;
			}
			cdata = (cdata & 0xFF);

			String returnData = ":";
			returnData += serialData + Integer.toHexString(cdata).toUpperCase();
			returnData += "\r";
			return returnData;
		}

		public void run() {
			try {
				byte[] inputData = data.getBytes();
				out.write(inputData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public CarClient(String portName) throws NoSuchPortException {
		portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		// 포트가 정상이면 CONNECT
		System.out.println("Connect Com Port!");
		try {
			connectSerial();
			System.out.println("Connect OK !!");
			(new Thread(new SerialWriter())).start(); //
		} catch (Exception e) {
			System.out.println("Connect Fail !!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		client = null;
		try {
			// CarClient st = new CarClient("COM5");
			// st = new CarClient("COM5");
			st = new CarClient("COM12");
			client = new CarClient("70.12.227.106", 1234);
			client.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
