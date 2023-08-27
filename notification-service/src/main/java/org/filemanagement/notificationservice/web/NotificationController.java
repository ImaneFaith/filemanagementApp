package org.filemanagement.notificationservice.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.filemanagement.notificationservice.entities.TokenEntity;
import org.filemanagement.notificationservice.models.http.HttpResponse;
import org.filemanagement.notificationservice.services.RedisTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notification Service ")
public class NotificationController {

    private final RedisTokenService redisTokenService;
    @PostMapping("/tokens")
    public ResponseEntity<HttpResponse> addToken(@RequestBody @Valid TokenEntity entity){

        redisTokenService.save(entity);
        return ResponseEntity.ok(HttpResponse.builder().status(HttpStatus.OK).message("token received successfully").build());

    }
 /*   @GetMapping(value = "/tokens")
    public ResponseEntity<List<TokenEntity>> getTokens(){

       return ResponseEntity.ok(redisTokenService.findAll());


    }

    @DeleteMapping("/tokens")
    public void deleteAll(){
        redisTokenService.deleteAll();
    }*/
}
