package org.gk2k23.models;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import java.util.UUID;

@MappedEntity
public class CollectionEntity {
    @Id
    private String id;
    private NewCollectionRequest collection;

    public CollectionEntity(NewCollectionRequest collection) {
        this.id = UUID.randomUUID().toString();
        this.collection = collection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NewCollectionRequest getCollection() {
        return collection;
    }

    public void setCollection(NewCollectionRequest collection) {
        this.collection = collection;
    }
}
