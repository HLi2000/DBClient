import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Client {
    public Client(){

    }

    public Img[] search(SearchInfo searchInfo) throws Exception{
        // Set up the body data
        Gson gson = new Gson();
        String jsonString = gson.toJson(searchInfo);

        URL myURL = new URL("https://dbservlet.herokuapp.com/search");
        //URL myURL = new URL("http://localhost:8080/DBServlet/search");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        // Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        // Write the body of the request
        try(OutputStream os = conn.getOutputStream()) {
            byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);
            os.write(body, 0, body.length);
        }

        // Read the body of the response
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), StandardCharsets.UTF_8));
        String resp;
        while ((resp = bufferedReader.readLine()) != null) {
            Gson gson2 = new Gson();
            Img[] img_a=gson2.fromJson(resp,Img[].class);
            for (Img img : img_a) {
                img.setThumbnail(getThumbnail(img));
            }
            return img_a;
        }

        bufferedReader.close();
        Img img=new Img();
        Img[] img_a={img};
        return img_a;
    }
    /*
    public InputStream getThumbnail(String filename) throws Exception{
        // Set up the body data
        byte[] body = filename.getBytes(StandardCharsets.UTF_8);

        URL myURL = new URL("https://dbservlet.herokuapp.com/thumbnail");
        //URL myURL = new URL("http://localhost:8080/DBServlet/search");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        // Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoOutput(true);

        // Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }

        // Read the body of the response
        InputStream file_stream=conn.getInputStream();
        if (file_stream!=null) {
            return file_stream;
        }
        return null;
    }
     */
    public InputStream getThumbnail(Img img) throws Exception{
        // Set up the body data
        String filename= img.getFile_name();
        byte[] body = filename.getBytes(StandardCharsets.UTF_8);

        URL myURL = new URL("https://dbservlet.herokuapp.com/thumbnail");
        //URL myURL = new URL("http://localhost:8080/DBServlet/search");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        // Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoOutput(true);

        // Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }

        // Read the body of the response
        InputStream file_stream=conn.getInputStream();
        if (file_stream!=null) {
            return file_stream;
        }
        return null;
    }

    public InputStream getImg(Img img) throws Exception{
        // Set up the body data
        String filename= img.getFile_name();
        byte[] body = filename.getBytes(StandardCharsets.UTF_8);

        URL myURL = new URL("https://dbservlet.herokuapp.com/img");
        //URL myURL = new URL("http://localhost:8080/DBServlet/search");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

        // Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoOutput(true);

        // Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }

        // Read the body of the response
        InputStream file_stream=conn.getInputStream();
        if (file_stream!=null) {
            return file_stream;
        }
        return null;
    }
}
