package com.recommendation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {
    @Value("${service.url.orderService}")
    private String orderServiceBaseUri;

    @Value("${service.url.retailService}")
    private String retailServiceBaseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    public List<String> makeRecommendation(Integer userId) {
        List<String> result = new ArrayList<>();

        // get categoryies that the user buy most;
        String orderServiceFinalUrl = String.format(orderServiceBaseUri,userId.toString() );
        System.out.println("orderServiceBaseUri = " + orderServiceBaseUri);
        String topCategory = restTemplate.getForObject(orderServiceFinalUrl, String.class);
        //System.out.println("top category is " + topCategory);
        String retailServiceFinalUrl = String.format(retailServiceBaseUrl, topCategory);
        System.out.println("retailserviceUrl = " + retailServiceFinalUrl);
        ResponseEntity<String[]> response = restTemplate.getForEntity(retailServiceFinalUrl,String[].class);
        String[] temp = response.getBody();
        System.out.println("temp.size = " + temp.length);
        for(int i = 0; i < temp.length; i++) {
            result.add(temp[i]);
        }
        return result;
    }
}
