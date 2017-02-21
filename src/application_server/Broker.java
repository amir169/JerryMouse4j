package application_server;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public class Broker {
    public static Response callHandler(String path, Request request) throws Exception {
        if(path.contains(".do") || !path.contains("."))
        {
            path = path.split("\\.")[0];
            String[] pathParts = path.split("/");
            if(pathParts.length == 0)
                return new FileRequestHandler(path).handle();

            String classPath = "app.";
            for(int i=1;i<pathParts.length-1;i++)
            {
                String temp = pathParts[i].toLowerCase();
                classPath += temp + ".";
            }
            String[] className = pathParts[pathParts.length - 1].split("-");
            for(String s: className)
            {
                classPath += s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
            }
            System.out.println(classPath);
            Class handler;
            try{
                handler = Class.forName(classPath);
            }
            catch (ClassNotFoundException e) {
                return Response.notFound();
            }
            Object obj = null;
            if(handler.newInstance() instanceof IServiceHandler)
            {
                obj = handler.getMethod("handle",Request.class).invoke(handler.newInstance(),request);
            }
            return (Response)obj;
        }
        else
            return new FileRequestHandler(path).handle();

    }
}
