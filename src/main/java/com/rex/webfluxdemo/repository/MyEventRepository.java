package com.rex.webfluxdemo.repository;

import com.rex.webfluxdemo.model.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> { // 1
    @Tailable
    Flux<MyEvent> findBy();
}