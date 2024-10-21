package org.gk2k23.repository;

import io.micronaut.data.mongodb.annotation.MongoFindQuery;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.gk2k23.models.CollectionEntity;

import java.util.List;

@MongoRepository
public interface CollectionRepository extends CrudRepository<CollectionEntity, String> {

    @MongoFindQuery("{ 'collection.name': :name }")
    List<CollectionEntity> findByCollectionName(String name);
}
