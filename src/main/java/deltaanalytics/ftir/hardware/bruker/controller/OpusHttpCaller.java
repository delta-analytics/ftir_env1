package deltaanalytics.ftir.hardware.bruker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Component
public class OpusHttpCaller {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpusHttpCaller.class);

    public String run(String completeMessage) {
        
        StringBuilder response = new StringBuilder();
        URL obj;
        try {
            LOGGER.info("run against Opus HTTP => " + completeMessage);
            obj = new URL(completeMessage);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            LOGGER.info(response.toString());

        } catch (Exception e) {
            LOGGER.error("", e);
            throw new RuntimeException(e);
        }
        return response.toString();
    }
}
