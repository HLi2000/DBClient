import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Client {
    public Client(){

    }

    public void makePostRequest() throws Exception{
        // Set up the body data
        Img img=new Img();
        img.setModality("CT");

        Gson gson = new Gson();
        String jsonString = gson.toJson(img);

        URL myURL = new URL("http://localhost:8080/DBServlet/search");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        // Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // Write the body of the request
        try(OutputStream os = conn.getOutputStream()) {
            byte[] body = jsonString.getBytes("utf-8");
            os.write(body, 0, body.length);
        }

        // Read the body of the response
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), StandardCharsets.UTF_8));
        String resp;
        while ((resp = bufferedReader.readLine()) != null) {
            Gson gson2 = new Gson();
            Img img2=gson2.fromJson(resp,Img.class);
            System.out.println(img2.getUrl());
        }
        bufferedReader.close();
    }
}
