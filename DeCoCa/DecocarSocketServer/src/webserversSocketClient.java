import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class webserversSocketClient {
	Socket socket;
	boolean rflag = true;
	public webserversSocketClient() {
	}
	public webserversSocketClient(String ip, int port) throws IOException {
		boolean flag = true;
		while (flag) {
			try {
				// Construct에서는 socket만 만듦
				// connection이 일어나 socket이 만들어지면?
				socket = new Socket(ip, port);
				if (socket != null && socket.isConnected()) {
					break; // socket은 한 번만 만들면 됨
				}
			} catch (Exception e) {
				System.out.println("Re-Try");
				// socket이 안 만들어지면 retry 해야 됨
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} // end while
		new Receiver(socket).start();
	}

	public void sendMsg(String msg) throws IOException {
		Sender sender = null;
		sender = new Sender(socket);
		sender.setMsg(msg);
		sender.start();
	}

	public void start() throws Exception {
		// Client 시작하라는 의미의 start
		/*
		 * try { // 보내는 thread? out = socket.getOutputStream(); dout= new
		 * DataOutputStream(out); in = socket.getInputStream(); din = new
		 * DataInputStream(in); dout.writeUTF("HELLO.."); // Client가 보내는 메시지
		 * 
		 * // 받는 thread? String str = din.readUTF(); System.out.println(str);
		 * }catch(Exception e) { throw e; }finally { if(din != null) { din.close(); }
		 * if(socket != null) { socket.close(); } }
		 */

		// 보내는 것은 여기에서 - 메시지
		Scanner sc = new Scanner(System.in);
		boolean sflag = true;
		while (sflag) {
			System.out.println("Input Msg.");
			String str = sc.next();
			sendMsg(str); // message를 보냄 - sendMsg 함수와 send라는 Thread?
			// Start가 되면 socket이 생성되고, Thread를 통해 주고 받음
			if (str.equals("q")) { // 'q' 입력하면 종료
				break;
			}
		}
		System.out.println("Bye...");
		sc.close();
	}

	// Inner Class로 정의
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
		// connection이 되면 client가 가만히 있어도 server가 메시지 보낼 수 있도록
		Socket socket;
		InputStream in;
		DataInputStream din;
		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);
		}
		public void run() {
			// 받는 역할만 수행
			try {
				while (rflag) {
					String str = din.readUTF();
					System.out.println(str);
				}
			} catch (Exception e) {
			}
		}
	}
	public static void main(String[] args) {
		webserversSocketClient client = null;
		try {
			client = new webserversSocketClient("70.12.60.110", 9999);
			//client = new Client("70.12.60.90", 8888);
			//client = new Client("70.12.60.111", 8877);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
