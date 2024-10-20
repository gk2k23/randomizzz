package org.gk2k23.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.gk2k23.models.CollectionEntity;
import org.gk2k23.models.NewCollectionRequest;
import org.gk2k23.models.Response;
import org.gk2k23.models.ResponseStatus;
import org.gk2k23.repository.CollectionRepository;

@io.micronaut.http.annotation.Controller("/api")
public class Controller {

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
}
