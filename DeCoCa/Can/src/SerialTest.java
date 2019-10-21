import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialTest implements SerialPortEventListener {
	public static String ss;
	public static DataOutputStream bos = null;
	private SerialPort serialPort;
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;
	private BufferedInputStream bin;
	private InputStream in;
	private OutputStream out;
	// ECU장비 제어

	private String saveNewMessage;
	static String data;
	static String[] status = { "", "100", "000", "080", "99", "88", "77", "66", "18", "1", "1", "1", "1" ,"9"};

	// 들어올 때는 버퍼드 써도 되는데
	// 나갈 떄 OutputStream을 쓰는 이유는
	// 상대는 자바 환경이 아닐 수도 있어서임.
	Socket socket;
	boolean rflag = true;

	static SerialTest st;

	public SerialTest() {
	}

	

	public SerialTest(String portName) throws NoSuchPortException {
		portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Connect ComPort!");
		try {
			connectSerial();
			System.out.println("Connect OK !!");
			(new Thread(new SerialWriter())).start();
		} catch (Exception e) {
			System.out.println("Connect Fail !!");
			e.printStackTrace();
		}

	}

	public void setPort(String portName) throws NoSuchPortException {
		portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Connect ComPort!");
		try {
			connectSerial();
			System.out.println("Connect OK !!");
			(new Thread(new SerialWriter())).start();
		} catch (Exception e) {
			System.out.println("Connect Fail !!");
			e.printStackTrace();
		}
	}

	private class SerialWriter implements Runnable {
		String data;

		public SerialWriter() {
			// 나 참가할게 메세지임.
			// 이걸 안 해주면 안 됨.
			this.data = ":G11A9\r";
		}

		public SerialWriter(String serialData) {
			/*
			 * W28: 데이터를 쏘겠다는 뜻 W28 00000000 000000000000 id data :W28 00000000 000000000000
			 * 53 checksum \r
			 */
			String sdata = sendDataFormat(serialData);
			System.out.println(sdata);
			this.data = sdata;
		}

		public String sendDataFormat(String serialData) {
			serialData = serialData.toUpperCase();
			System.out.println(serialData +"@@");
			char c[] = serialData.toCharArray();
			int cdata = 0;
			for (char cc : c) {
				cdata += cc;
			}

			// 비트연산
			System.out.println("before 0xff : " + cdata);
			cdata = (cdata & 0xFF);
			System.out.println("after 0xff : " + cdata);
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

	public void connectSerial() throws Exception {
		// 다른 아이가 쓰고 있다.

		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			commPort = portIdentifier.open(this.getClass().getName(), 100);
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // 통신속도(Serial 속도) 서로 달라도 상관 없음.
						SerialPort.DATABITS_8, // 데이터 비트
						SerialPort.STOPBITS_1, // stop 비트
						SerialPort.PARITY_NONE); // 패리티
				// 전송하는 데이터를 검증하겠다는 뜻임.
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				// 데이터를 Serial로 쏠 수 있음.
				out = serialPort.getOutputStream();

			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	public void sendData(String data) {
		SerialWriter sw = new SerialWriter(data);
		new Thread(sw).start();
	}

	public static void main(String[] args) {

		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					st = new SerialTest("COM7");
					while (true) {
						st.sendData("W28"+statustoString());
						Thread.sleep(1500);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread t= new Thread(r);
		t.start();

	}
	public static String statustoString() {
		String str = "";
		for (int i = 1; i < 14; i++) {
			str += status[i];
		}
		return str;
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

				ss = new String(readBuffer);
				boolean check = checkSerialData(ss);
				System.out.println("ResultCheck:" + check + "||");
				System.out.println("Receive Low Data:" + ss + "||");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

		/*
		 * //철민이네 if (ss.charAt(11) == '1') { boolean result = checkSerialData(ss);
		 * System.out.println("Result : " + result);
		 * System.out.println("Receive Low Data:" + ss + "||");
		 * System.out.println("온도"); st.sendMsg("온도"); } else if (ss.charAt(11) == '2')
		 * { boolean result = checkSerialData(ss); System.out.println("Result : " +
		 * result); System.out.println("Receive Low Data:" + ss + "||");
		 * System.out.println("습도"); st.sendMsg(ss);
		 */ }

	public boolean checkSerialData(String data) {
		boolean check = false;
		// :U2800000050000000000000002046		
		String checkData = data.substring(1, 28);
		checkData = data.substring(1, 28);
		String checkSum = data.substring(28, 30);
		if(data.charAt(1)=='U') {
		for (int i = 1; i <= 3; i++) {
			status[i] = data.substring(i * 3 + 1, i * 3 + 4);
		}
		for (int i = 1; i <= 5; i++) {
			status[i + 3] = data.substring(i * 2 + 11, i * 2 + 13);
		}
		for (int j = 9, i = 23; i <= 26; i++, j++)
			status[j] = data.substring(i, i + 1);
		}
		char c[] = checkData.toCharArray();
		int cdata = 0;

		// st.sendData(saveNewMessage);
		// 일단 엔진 사용여부
		/*
		 * String checkEngine = data.substring(7, 8);
		 * saveNewMessage.concat(checkData.substring(1, 7));
		 * 
		 * System.out.println(saveNewMessage); if(checkEngine.equals("00")) { engine
		 * ="01"; saveNewMessage.concat("88"); //data.replace("00", "88");
		 * 
		 * System.out.println(saveNewMessage); }else{
		 * 
		 * }
		 * 
		 * saveNewMessage.concat(checkData.substring(9, 28));
		 * 
		 * System.out.println(saveNewMessage); System.out.println("동작이 됬음");
		 * 
		 * st.sendData(saveNewMessage); }
		 */

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

}
