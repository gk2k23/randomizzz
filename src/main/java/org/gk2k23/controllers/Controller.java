package org.gk2k23.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.gk2k23.models.*;
import org.gk2k23.repository.CollectionRepository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@io.micronaut.http.annotation.Controller("/api")
public class Controller {

    private static final Random random = new SecureRandom();

    @Inject
    private CollectionRepository repository;

    @Get(uri = "/healthcheck", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<String> healthCheck() {
        return HttpResponse.ok("{\"status\":\"hello! it's up\"}");
    }

    @Post(uri = "/collection", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Response> saveCollection(@Body NewCollectionRequest request) {
        CollectionEntity entity = repository.save(new CollectionEntity(request));
        return HttpResponse.ok(new Response(ResponseStatus.SUCCESS.name(), "Collection saved successfully", entity.getId()));
    }

    @Get(uri = "pickAtRandom/{collectionName}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Response> pickAtRandom(@PathVariable String collectionName, @QueryValue(defaultValue = "1") Integer count) {
        CollectionEntity entity = repository.findByCollectionName(collectionName).stream().findFirst().orElse(null);
        if (entity == null) {
            return HttpResponse.badRequest(new Response(ResponseStatus.FAILURE.name(), "Collection not found for given name"));
        }
        List<String> results = new ArrayList<>();
        while (count > 0 && !entity.getCollection().getItems().isEmpty()) {
            results.add(pickAnItemAtRandom(entity.getCollection().getItems(), collectionName));
            count--;
        }
        return HttpResponse.ok(new Response(ResponseStatus.SUCCESS.name(), "Successfully picked items at random", results));
    }

    private String pickAnItemAtRandom(List<Item> items, String pathName) {
        Item item = items.get(random.nextInt(items.size()));
        if (item.getChildren() == null || item.getChildren().isEmpty()) {
            items.remove(item);
            return pathName + "/" + item.getName();
        } else if(item.getChildren().size() == 1) {
            items.remove(item);
            return pathName + "/" + item.getName() + "/" + item.getChildren().get(0).getName();
        } else {
            return pickAnItemAtRandom(item.getChildren(), pathName + "/" + item.getName());
        }
    }
}
