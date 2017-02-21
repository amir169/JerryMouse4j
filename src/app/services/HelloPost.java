package app.services;

import application_server.IServiceHandler;
import application_server.Request;
import application_server.Response;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public class HelloPost implements IServiceHandler{

    @Override
    public Response handle(Request request) throws Exception {

        return Response.ok("salam " + request.getBody());
    }
}
