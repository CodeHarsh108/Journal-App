package net.engharsh.journalApp.service;

import net.engharsh.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static  final String apiKey = "0d8171171c8c2eb110f4c7c2fab87116";
    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
//
//    @Autowired
//    private RestTemplate restTemplate;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(String city){
//        String finalAPI =  API.replace("CITY", city).replace( "API_KEY", apiKey);
        String finalAPI = String.format(API, apiKey, city);
        User user =  User.builder().userName("MP").password("Indore").build();
        HttpEntity<User> httpEntity = new HttpEntity<>(user);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

}
