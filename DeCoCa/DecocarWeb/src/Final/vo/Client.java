package Final.vo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	String ip;
	int port;
	int cmd;
	ServerSocket serverSocket;
	Socket socket;
	OutputStream out;
	DataOutputStream dout;
	String msg ="";

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	public void setMsg(int cmd, int carId, int scheduleId) {		
		this.cmd = cmd;
		switch (this.cmd) {
		case 0: // 1. N번의 차량 에게 일정 전달하기
			this.msg = "sche-"+carId+"-"+scheduleId;
			break;
		case 1:// 2. 모든 차량에게 상태를 업데이트 하라고 하기
			this.msg = "selectAll";
			break;
		case 2:// 3. N번의 차량에게 상태를 업데이트 하라고 하기
			this.msg = "select-"+carId;
			break;
		}
	}
	public void sendMsg() throws Exception {
		try {			
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			dout.writeUTF(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
			
	}

	public void startClient() throws Exception {
		System.out.println("Client Start");
		try {
			socket = new Socket(ip, port);
			System.out.println(socket.getInetAddress());
			sendMsg();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null)
				socket.close();
		}
	}

	public static void main(String[] args) {
		Client c = new Client("70.12.60.110", 9999);
		c.setMsg(0, 1234, 5678);
		try {
			c.startClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}