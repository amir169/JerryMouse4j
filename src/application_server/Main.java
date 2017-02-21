package application_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Amir Shams on 2/20/2017.
 */
public class Main {

    public static void main(String[] args) throws Exception
    {
        ServerSocket welcomeSocket = new ServerSocket(5050);
        while(true)
        {
            Socket connectionSocket = welcomeSocket.accept();
            ExecutorService pool = Executors.newCachedThreadPool();
            pool.submit(new Thread(new RequestThread(connectionSocket)));
        }
    }

}
