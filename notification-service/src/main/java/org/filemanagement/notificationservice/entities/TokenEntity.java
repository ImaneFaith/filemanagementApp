package org.filemanagement.notificationservice.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class TokenEntity {
    @Id
    private String username;

    @Pattern(regexp = "[0-9a-zA-Z\\-_]*")
    @NotBlank
    private String token;
}
