package com.hung.controller;

import com.hung.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ArticleControllerTest {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Test
    void saveArticle() {
        RestTemplate client = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Article article = new Article(5, "《Python 深入淺出》");
        HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);
        // postForObject() 響應正文
        // 如果需要回傳整個實體，可以用 postForEntity() 方法
        String body = client.postForObject("http://localhost:8080/articles", entity, String.class);
        System.out.println(body);
    }

    @Test
    void getArticleById() {
        RestTemplate client = restTemplateBuilder.build();
        // RESTful 介面以 JSON 格式來承載資料，回傳的是 JSON 串，因此類型參數指定 String 類型
        ResponseEntity<String> entity =
                client.getForEntity("http://localhost:8080/articles/{id}",
                        String.class, 2);
        System.out.println(entity.getBody());
    }

    @Test
    void getAllArticles() {
        RestTemplate client = restTemplateBuilder.build();
        // 如果只需要響應正文，使用 getForObject() 方法
        String body = client.getForObject("http://localhost:8080/articles",
                String.class);
        System.out.println(body);
    }

    @Test
    void updateArticle() {
        RestTemplate client = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Article article = new Article(3, "《Vue.js 3.1從入門到實戰》");
        HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);
        // put() 沒有回傳值，如果需要接收響應訊息，可以呼叫 exchange() 方法
        //client.put("http://localhost:8080/articles", entity);
        ResponseEntity<String> responseEntity = client.exchange(
                "http://localhost:8080/articles",
                HttpMethod.PUT,
                entity,
                String.class);
        System.out.println(responseEntity.getBody());
    }

    @Test
    void deleteArticle() {
        RestTemplate client = restTemplateBuilder.build();
        // delete() 沒有回傳值，如果需要接收響應訊息，可以呼叫 exchange() 方法
        //client.delete("http://localhost:8080/articles/{id}", 1);

        ResponseEntity<String> responseEntity = client.exchange(
                "http://localhost:8080/articles/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                2);
        System.out.println(responseEntity.getBody());
    }
}