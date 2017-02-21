package application_server;

import java.io.*;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public class FileRequestHandler{

    private String filePath;

    public FileRequestHandler(String filePath) {
        if(filePath.equals("/"))
            this.filePath = "src/app/static_contents/index.html";
        else
            this.filePath = "src/app/static_contents" + filePath;
    }

    public Response handle() throws Exception{
        InputStream input;
        System.out.println(filePath);
        try {
            input = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            return Response.notFound();
        }
        byte[] array = new byte[Short.MAX_VALUE];
        try {
            input.read(array);
        } catch (IOException e) {
            return Response.notFound();
        }

        String string = new String(array);
        return Response.ok(string);
    }

}
