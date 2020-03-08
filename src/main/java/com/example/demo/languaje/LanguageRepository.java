package com.example.demo.languaje;

import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.MongoRepository;

@Configuration
public interface  LanguageRepository extends MongoRepository<Language,Integer>{
}