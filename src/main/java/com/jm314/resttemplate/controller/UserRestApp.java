package com.jm314.resttemplate.controller;

import com.jm314.resttemplate.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class UserRestApp {
    private final static String API_USERS_URL = "http://91.241.64.178:7081/api/users/";
    private static String cookies;
    private static String resultResponse;


    public static void main(String[] args) {
        getAllUsersApi();
        createUserApi(new User(3L, "James", "Brown", (byte) 33));
        updateUserApi(new User(3L, "Thomas", "Shelby", (byte) 33));
        deleteUserApi(3L);
        System.err.println(resultResponse);
    }

    private static void getAllUsersApi() {
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestBody = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(API_USERS_URL, HttpMethod.GET, requestBody, String.class);
        System.out.println(response);
        cookies = response.getHeaders().getFirst("Set-Cookie");

    }


    private static void createUserApi(User newUser) {
        var restTemplate = new RestTemplate();

        var headers = new HttpHeaders();
        headers.set("Cookie", cookies);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);

        ResponseEntity<String> userResponseEntity = restTemplate.postForEntity(API_USERS_URL, requestBody, String.class);
        resultResponse = userResponseEntity.getBody();
    }

    private static void updateUserApi(User updateUser) {
        var restTemplate = new RestTemplate();

        var headers = new HttpHeaders();
        headers.set("Cookie", String.join(";", cookies));
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<User> requestBody = new HttpEntity<>(updateUser, headers);

        ResponseEntity<String> userResponseEntity = restTemplate
                .exchange(API_USERS_URL, HttpMethod.PUT, requestBody, String.class);
        resultResponse += userResponseEntity.getBody();
    }

    private static void deleteUserApi(Long id) {
        var restTemplate = new RestTemplate();

        var headers = new HttpHeaders();
        headers.set("Cookie", String.join(";", cookies));
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        
        HttpEntity<String> requestBody = new HttpEntity<>(headers);

        ResponseEntity<String> userResponseEntity = restTemplate
                .exchange(API_USERS_URL + id, HttpMethod.DELETE, requestBody, String.class);
        resultResponse += userResponseEntity.getBody();
    }


}
