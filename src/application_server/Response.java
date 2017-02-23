package application_server;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public class Response {


    private Response(){}

    private String body;
    private Response(String body)
    {
        this.body = body;
    }

    protected String writeAsString() {
        return body;
    }

    public static Response notFound() {
        String status = "HTTP/1.1 404 Not Found\r\n" +
                        "Connection: closed\r\n\r\n";
        return new Response(status);
    }
    public static Response ok() {
        String status = "HTTP/1.1 200 OK\r\n" +
                        "Connection: closed\r\n\r\n";
        return new Response(status);
    }
    public static Response ok(String body) {
        String status = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Connection: closed\r\n\r\n";
        return new Response(status + body);
    }
    public static Response ok(String body,String contentType)
    {
        String status = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: " + contentType + "\r\n" +
                        "Connection: closed\r\n\r\n";
        return new Response(status + body);
    }

    public static Response internalServerError() {
        String status = "HTTP/1.1 500 Internal Server Error\r\n" +
                "Connection: closed\r\n\r\n";
        return new Response(status);
    }
}
