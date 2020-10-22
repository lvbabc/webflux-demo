package com.rex.webfluxdemo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.rex.webfluxdemo.model.MyEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import javax.annotation.Resource;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxDemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(WebfluxDemoApplication.class, args);
    }

    @Bean   // 1
    @Resource
    public CommandLineRunner initData(ReactiveMongoOperations mongoTemplate) {  // 2
        return (String... args) -> {    // 3
            mongoTemplate.dropCollection(MyEvent.class).block();    // 4
            mongoTemplate.createCollection(MyEvent.class,
                    CollectionOptions.empty().maxDocuments(200).size(100000).capped()).block(); // 5
        };
    }
}
