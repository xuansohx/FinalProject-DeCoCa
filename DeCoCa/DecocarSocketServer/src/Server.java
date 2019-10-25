import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class SThread extends Thread {
	int port;
	Socket socket;
	OutputStream out;
	DataOutputStream dout;
	InputStream in;
	DataInputStream din;
	public SThread(Socket socket) throws IOException {
		this.socket = socket;
		out = socket.getOutputStream();
		dout = new DataOutputStream(out);
		in = socket.getInputStream();
		din = new DataInputStream(in);
	}
	public void run() {
		try {
			String str = din.readUTF();
			System.out.println(socket.getInetAddress() + " : " + str);
			dout.writeUTF("This Feeling is hard to find");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dout != null)
				try {
					dout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (din != null)
				try {
					din.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
public class Server {
	boolean flag = true;
	ServerSocket serverSocket;
	Map<String, DataOutputStream> map = new HashMap<>();
	Map<String, String> nick = new HashMap<>();
	Map<String, String> nickip = new HashMap<>();
	ArrayList<String> userList = new ArrayList<String>();

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server Start..");
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					while (flag) {
						System.out.println("Server Ready");
						Socket socket = serverSocket.accept();
						new ReCThread(socket).start();
						System.out.println(socket.getInetAddress() + " Connected");
						sendMsg(socket.getInetAddress() + " 님이 접속했습니다");
					}
					System.out.println("Server Dead...");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r).start();
	}
	public void start() throws IOException {
		Scanner sc = new Scanner(System.in);
		try {
			boolean inflag = true;
			System.out.println("Input Msg");
			while (inflag) {
				String instr = sc.next();
				sendMsg(instr);
				if (instr.equals("q"))
					break;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			sc.close();
		}
	}
	public void sendMsg(String msg) {
		SendThread s = new SendThread();
		s.setMsg(msg);
		s.start();
	}
	public void sendWhisper(String msg, String ip) {
		SendThread s = new SendThread();
		s.setMsg(msg);
		s.setTarget(ip);		
		s.setCmd(1);
		s.start();
	}
	class SendThread extends Thread {
		String msg;
		int cmd = 0;
		String target;
		
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public void setCmd(int cmd) {
			this.cmd = cmd;
		}
		public void setTarget(String t) {
			this.target = t;
		}
		public void run() {
			if (cmd == 0) {
				Collection<DataOutputStream> col = map.values();
				Iterator<DataOutputStream> it = col.iterator();
				while (it.hasNext()) {
					try {
						it.next().writeUTF(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (cmd == 1) {
				DataOutputStream doutt;
				String ip = nickip.get(target);
				doutt = map.get(ip);
				try {
					doutt.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	class ReCThread extends Thread {
		Socket socket;
		InputStream in;
		DataInputStream din;
		OutputStream out;
		DataOutputStream dout;
		boolean rflag = true;
		String ip;
		String name;
		public ReCThread() {
		}
		public ReCThread(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			ip = socket.getInetAddress().toString();
			if (nick.get(ip) != null)
				name = nick.get(ip);
			map.put(ip, dout);
			System.out.println("접속자 수: " + map.size());
		}
		public void run() {
			try {
				while (rflag) {
					String str = din.readUTF();
					if (str == null || str.equals(""))
						continue;
					System.out.println(str);
					String sendId;
					boolean flag = true;
					// web 서버에서 메세지를 보내면 이를 명령어로 해석해서 작업을 한다.
					// 1. N번 차량에게 일정을 전달하기
					// sche-차량id-일정id
					// 2. 모든 차량의 정보를 알려줘!
					// selectAll
					// 3. N 번 차량의 정보를 알려줘!
					// select-차량id
					String[] cmd = str.split("-");
					System.out.println(cmd[0]+"first cmd");
					if (str.equals("selectAll")) {// 2.
						sendMsg("updateState");
					} else if (cmd[0].equals("select")) {// 3.
						String target = cmd[1];
						sendWhisper("updateState", target);
					} else if (cmd[0].equals("sche")) { // 1.
						String target = cmd[1];
						String schedule = cmd[2];
						System.out.println(target+" "+schedule);
						sendWhisper("sche-" + schedule, target);
					}else if(cmd[0].equals("nick")) {
						nick.put(ip, cmd[1]);
						nickip.put(cmd[1],ip);
						System.out.println(ip+"'s carid is "+cmd[1]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("비정상 아웃:" + ip);
				map.remove(ip);
				nickip.remove(nick.get(ip));
				nick.remove(ip);
				System.out.println("나간후접속자수:" + map.size());				
			} finally {
				
			}
		}
		public String getClients() {
			String a = "[";
			Set s = map.keySet();
			Iterator it = s.iterator();
			boolean f = true;
			while (it.hasNext()) {
				String k = (String) it.next();
				if (nick.containsKey(k))
					if (f) {
						a += " " + nick.get(k);
						f = false;
					} else
						a += " , " + nick.get(k);
				else if (f) {
					a += " " + k;
					f = false;
				} else
					a += " , " + k;
			}
			return a + " ]";
		}
	}
	public static int getNicks(String c) {
		int l = c.length();
		String re = "";
		int i = 0;
		for (i = 0; i < l; i++) {
			if (c.charAt(i) != ' ') {
				re += c.charAt(i);
			} else
				break;
		}
		return i;
	}
	public static void main(String[] args) throws IOException {
		Server s = new Server(9999);
		s.start();
	}
}
