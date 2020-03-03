package part2.chaper13;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/2
 **/
public class AppServer extends Thread{
    private int port ;
    private static final int DEFAULT = 2722;

    private  volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();
    private final static ExecutorService executor = Executors.newFixedThreadPool(10);

    public AppServer(int port) {
        this.port = port;
    }

    public AppServer() {
        this(DEFAULT);
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (start){
                Socket socketClient = serverSocket.accept();
                ClientHandler clientHandler =  new ClientHandler(socketClient);
                executor.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            dispose();
        }
    }

    private void dispose() {
    }

    public void shutdown(){
        this.start = false;
        this.interrupt();
    }
}
