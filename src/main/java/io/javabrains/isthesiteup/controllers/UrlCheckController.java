package io.javabrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";
    
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){

        String returnMessage="";

        try {
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //System.out.println(conn.getResponseCode());
            int requestCodeCategory = conn.getResponseCode() / 100;
            //System.out.println(requestCodeCategory);
            if(requestCodeCategory == 2 || requestCodeCategory == 3){
                returnMessage = SITE_IS_UP;
            }
            else{
                returnMessage = SITE_IS_DOWN;
            }

        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }

        return returnMessage;
    }

}
