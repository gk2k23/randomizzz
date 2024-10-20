package org.gk2k23.repository;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.gk2k23.models.CollectionEntity;

@MongoRepository
public interface CollectionRepository extends CrudRepository<CollectionEntity, String> {

}
