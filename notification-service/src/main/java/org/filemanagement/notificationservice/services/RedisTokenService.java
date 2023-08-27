package org.filemanagement.notificationservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.notificationservice.entities.TokenEntity;
import org.filemanagement.notificationservice.repositories.RedisRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisTokenService {

    private final RedisRepository redisRepository;


    public TokenEntity save(TokenEntity entity) {
        return redisRepository.save(entity);
    }


    public Optional<TokenEntity> findById(String id) {
        return redisRepository.findById(id);
    }

    public List<TokenEntity> findAll() {
        List<TokenEntity> entites = redisRepository.findAll();
        entites.forEach(entity -> log.info("entity {} ",entity));
        return entites;
    }

    public void deleteAll() {

        redisRepository.deleteAll();
    }




}
