package org.filemanagement.notificationservice.repositories;

import org.filemanagement.notificationservice.entities.TokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends ListCrudRepository<TokenEntity, String> {
}
