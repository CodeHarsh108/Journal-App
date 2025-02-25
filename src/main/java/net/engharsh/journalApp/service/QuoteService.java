package net.engharsh.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuoteService {
    private static final String apiKey = "b7f44734c2mshd9277cb3ec0c5d6p178a8ajsn92dc239a24f3";


    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;


    public String getWeather(String city){
        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
        restTemplate.exchange(finalAPI, HttpMethod.GET, null)
    }
}
