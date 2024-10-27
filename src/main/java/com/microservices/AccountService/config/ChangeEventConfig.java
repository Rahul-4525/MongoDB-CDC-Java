package com.microservices.AccountService.config;

import com.microservices.AccountService.entity.User;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.messaging.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Component
public class ChangeEventConfig implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    ConcurrentHashMap cacheMap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        MessageListenerContainer container = new DefaultMessageListenerContainer(mongoTemplate);

        container.start();

        MessageListener<ChangeStreamDocument<Document>, User> listener = message -> {
            this.cacheMap.put(message.getBody().getUserId(),
                    message.getRaw().getUpdateDescription().getUpdatedFields());
            System.out.println(message.getRaw().getUpdateDescription().getUpdatedFields());
        };

        AggregationOperation matchOperation = new MatchOperation(
                Criteria.where("updateDescription.updatedFields").exists(true)
        );



        ChangeStreamOptions changeStreamOptions = ChangeStreamOptions.builder()
                .fullDocumentLookup(FullDocument.UPDATE_LOOKUP)
                .filter(newAggregation(matchOperation))
                .build();

        ChangeStreamRequest.ChangeStreamRequestOptions options =
                new ChangeStreamRequest
                        .ChangeStreamRequestOptions("Account", "users",
                        changeStreamOptions);

        container.register(new ChangeStreamRequest<>(listener, options), User.class);

    }

}

