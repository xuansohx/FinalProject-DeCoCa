package com.example.cluss;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Server extends Thread {
    boolean flag = true;
    ServerSocket serverSocket;
    Map<String, DataOutputStream> map = new HashMap<>();
    Map<String, String> nick = new HashMap<>();
    Map<String, String> nickip = new HashMap<>();
    ArrayList<String> userList = new ArrayList<String>();
    Client client;
    private int port;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Start..");
            while (flag) {
                System.out.println("Server Ready");
                Socket socket = serverSocket.accept();

                new ReCThread(socket).start();
                System.out.println(socket.getInetAddress() + " Connected");
                sendMsg(socket.getInetAddress() + "Connected");
            }
            System.out.println("Server Dead...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setClient(Client client){
        this.client = client;
    }
    public Server(int port) throws IOException {
        this.port = port;
    }
    public void sendMsg(String msg) {
        SendThread s = new SendThread();
        s.setMsg(msg);
        s.start();
    }
    class SendThread extends Thread {
        String msg;
        int cmd = 0;
        String target;
        String me;

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setMe(String me) {
            this.me = me;
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
            System.out.println("Nuber of Clints: " + map.size());
        }
        public void run() {
            try {
                while (rflag) {
                    String str = din.readUTF();
                    System.out.println("str"+str);
                    client.makeStatus(str);
                    client.httpTask = new HttpTask("http://70.12.60.110/DeCoCa/statusCenter.mc?carstatus="+str);
                    client.httpTask.execute();
                    if (str == null || str.equals(""))
                        continue;
                    else if(str.equals("EngineStart")){
                    }
                    System.out.println(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("out:" + ip);
                map.remove(ip);
                nickip.remove(nick.get(ip));
                nick.remove(ip);
                System.out.println("left clients" + map.size());
                if (din != null) {
                    try {
                        din.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
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
                else
                if (f) {
                    a += " " + k;
                    f = false;
                }
                else
                    a += " , " + k;
            }
            return a + " ]";
        }
    }
}
