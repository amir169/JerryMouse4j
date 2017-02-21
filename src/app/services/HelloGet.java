package app.services;

import application_server.IServiceHandler;
import application_server.Request;
import application_server.Response;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public class HelloGet implements IServiceHandler {
    @Override
    public Response handle(Request request) throws Exception {
        String name = request.getQuery().get("name");
        String family = request.getQuery().get("family");

        return Response.ok("salam " + name + " " + family);
    }
}
