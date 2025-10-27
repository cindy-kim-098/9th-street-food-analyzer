import java.net.*;
import java.io.*;
import java.net.http.*;
import com.google.gson.*;

/**
 * Create a post request to a 201 REST server
 * to record json information for compsci 201,
 * fall 2021.
 * @Version 2: Fall 2024, updated network code
 * and changed Person201 to include 9th street eatery as final string
 * @Version 3: Fall 2025, modified URL to be current for Fall 2025 semester
 * 
 * @author Owen Astrachan, ola@duke.edu
 */

public class PostPerson {

    static String URL = "https://courses.cs.duke.edu/compsci201/fall25/data/p0/upload201.php";

    /**
     * Create a post request to the specified URL and
     * send a json string corresponding to the specified Person201
     * object.
     * @param url is the URL of a rest server accepting json
     * @param p is the Person201 object sent to server
     */
    public void postJson(String url, Person201 p) {
        Gson gs = new Gson();
        String str = gs.toJson(p);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(str))
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("printing response from POST request");
            System.out.println(response.body());
            System.out.printf("code = %d\n",response.statusCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("post request likely failed");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        PostPerson pp = new PostPerson();

        Person201 p = new Person201("Cindy",25.1169,121.5293,"Subway");
        pp.postJson(URL,p);
    }
}
