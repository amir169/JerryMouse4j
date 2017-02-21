package application_server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public class RequestThread implements Runnable {

    private Socket connection;
    RequestThread(Socket connection)
    {
        this.connection = connection;
    }

    public void run() {
        Request request = null;
        Response response = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(connection.getInputStream());
            byte[] arr = new byte[Short.MAX_VALUE];
            stream.read(arr);
            String message = new String(arr);
            String address = message.split(" ")[1].split("\\?")[0];
            request = new Request(message);
            response = Broker.callHandler(address,request);

        } catch (Exception e) {
            response = Response.internalServerError();
        }

        try {
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());
            outToClient.writeBytes(response.writeAsString());
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
