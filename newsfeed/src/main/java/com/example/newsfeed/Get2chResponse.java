package com.example.newsfeed;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Get2chResponse {

    private static String BASE_URL = "https://2ch.hk/po/1.json";

    public String getJson() {
        HttpURLConnection connection = null ;
        InputStream is = null;

        try {
            connection = (HttpURLConnection) ( new URL(BASE_URL)).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            StringBuffer buffer = new StringBuffer();
            is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            connection.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { connection.disconnect(); } catch(Throwable t) {}
        }

        return null;
    }
}
