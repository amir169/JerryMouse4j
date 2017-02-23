package application_server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir Shams on 2/21/2017.
 */
public class Request {

    private String method;
    private Map<String,String> headers;
    private Map<String,String> query;
    private String body;

    Request(String HTTPMessage)
    {

        headers = new HashMap<>();
        query = new HashMap<>();

        String[] lines = HTTPMessage.split("\\r?\\n");

        String[] firstLine = lines[0].split(" ");
        this.method = firstLine[0];
        String[] addressParts = firstLine[1].split("\\?");
        if(addressParts.length > 1)
        {
            String[] queries = addressParts[1].split("&");
            for(String q : queries)
            {
                String[] pair = q.split("=");
                query.put(pair[0],pair[1]);
            }
        }

        int i = 1;
        String line = lines[i];
        while(!line.isEmpty())
        {
            String[] headerParts = line.split(":");
            headers.put(headerParts[0],headerParts[1]);
            i++;
            line = lines[i];
        }
        body = "";
        while(i<lines.length)
        {
            body += lines[i];
            i++;
        }
    }

    public String getMethod() {
        return method;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public String getQuery(String parameter){
        return query.get(parameter);
    }

    public String getBody() {
        return body;
    }
}
