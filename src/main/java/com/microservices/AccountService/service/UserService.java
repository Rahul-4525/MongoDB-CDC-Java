package com.microservices.AccountService.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.BsonDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.microservices.AccountService.entity.User;
import com.microservices.AccountService.entity.User.AccountSettings;
import com.microservices.AccountService.entity.User.Address;
import com.microservices.AccountService.entity.User.ContactInfo;
import com.microservices.AccountService.entity.User.EmploymentDetails;
import com.microservices.AccountService.entity.User.Preferences;
import com.microservices.AccountService.entity.User.Subscription;
import com.microservices.AccountService.repo.UserRepo;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    ConcurrentHashMap<String, BsonDocument> cacheMap;

    public void saveUsers() {
       List<User> users;
       try{
          byte[] json_bytes =  new FileInputStream("src/main/resources/data.json").readAllBytes();
          users = new ObjectMapper().readValue(
                  json_bytes, new TypeReference<>() {
                  }
          );
       }catch (IOException ex){
           throw new RuntimeException(ex);
       }
        this.userRepo.saveAll(users);
    }

    public CompletableFuture<Optional<BsonDocument>> waitForKeyAsync(String key, long timeout) {
        return CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < timeout) {
                BsonDocument cachedData = cacheMap.get(key);
                if (cachedData != null) {
                    return Optional.of(cachedData);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            return Optional.empty();
        });
    }

    public void updateUser(String userId, String userName){
        mongoTemplate.update(User.class)
                .matching(where("userId").is(userId))
                .apply(new Update().set("username", userName)).first();
    }
}
