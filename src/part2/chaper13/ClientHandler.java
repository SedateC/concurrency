package part2.chaper13;

import java.net.Socket;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/2
 **/
public class ClientHandler extends Thread{

    private volatile Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {

    }
}
