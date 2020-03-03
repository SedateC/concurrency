package part2.chaper13;

import java.io.*;
import java.net.Socket;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/2
 **/
public class ClientHandler implements Runnable{

    private volatile Socket socket;
    private volatile boolean running = true;

    public ClientHandler(Socket socket){
        this.socket = socket;
        System.out.println("come from client - new one" );
    }


    @Override
    public void run() {
        try {
            InputStream inputStream =  socket.getInputStream();
            OutputStream outputStream  = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            while (running){
                String message  = bufferedReader.readLine();
                if (null == message) break;
                System.out.println("come from client - " +message);
                bufferedWriter.write("client "+message);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.shutdown();
        }
    }
    public void shutdown() {
        if (!running){
            return;
        }
        this.running = false;
        System.out.println(" client - shutdown myself");
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
