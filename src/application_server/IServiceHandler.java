package application_server;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public interface IServiceHandler{
   Response handle(Request request) throws Exception;
}
