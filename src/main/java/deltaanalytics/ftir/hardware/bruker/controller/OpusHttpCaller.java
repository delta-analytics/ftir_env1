package deltaanalytics.ftir.hardware.bruker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class OpusHttpCaller {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpusHttpCaller.class);

    public void run(String completeMessage) {
        LOGGER.info("run " + completeMessage);
        URL obj;
        try {
            obj = new URL(completeMessage);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();
            LOGGER.info("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            LOGGER.info(response.toString());

        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}
