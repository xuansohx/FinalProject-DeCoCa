package com.example.DeCoCar;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

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

import static com.example.DeCoCar.MainActivity.init;

public class Server extends Thread {
    int carid;
    boolean flag = true;
    ServerSocket serverSocket;
    Map<String, DataOutputStream> map = new HashMap<>();
    Map<String, String> nick = new HashMap<>();
    Map<String, String> nickip = new HashMap<>();
    ArrayList<String> userList = new ArrayList<String>();
    Client client;
    private int port;
    static int Serverkph =0;
    static int temp =0;
    static int door =0;
    static int btr =0;
    Runnable signal = new Runnable() {
        @Override
        public void run() {
            while (true){
                if(init){
                    sendMsg("1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    double lat, lng;
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
                HttpTask httpTask;
                httpTask= new HttpTask("http://70.12.60.110/DeCoCa/getPastState.mc?carid="+carid);
                httpTask.execute();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread t = new Thread(signal);
                t.start();
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
    public void setCarid(int carid) {
        this.carid = carid;
    }
    public void setLat(double lat){
        this.lat=lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void sendMsg(String msg) {
        SendThread s = new SendThread();
        s.setMsg(msg);
        s.start();
    }
    public void sendEndtoServer(){
        sendMsg("2");
        client.httpTask = new HttpTask("http://70.12.60.110/DeCoCa/getcarEnd.mc?carid="+carid);
        client.httpTask.execute();
    }
    public void sendStarttoServer(){
        client.httpTask = new HttpTask("http://70.12.60.110/DeCoCa/getcarStart.mc?carid="+carid);
        client.httpTask.execute();
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
                   // Message message = Message.obtain();
                    String str = din.readUTF();
                    //System.out.println("str"+str);
                    client.makeStatus(str);
                    str=str.substring(0,14);
                    btr=Integer.parseInt(str.substring(0,3));
                    Serverkph =Integer.parseInt(str.substring(3,6));
                    temp=Integer.parseInt(str.substring(8,10));
                    door=Integer.parseInt(str.substring(10,11));
                   // ContactFragment.setContFrag();
                    //0800 0000 0000 00
                    ContactFragment.uitest();
                    client.httpTask = new HttpTask("http://70.12.60.110/DeCoCa/statusCenter.mc?carid="+carid+"&carstatus="+str+"&lat="+lat+"&lng="+lng);
                    client.httpTask.execute();
                    if (str == null || str.equals(""))
                        continue;
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
    }
}
