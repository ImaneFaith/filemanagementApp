package org.filesmanagement.authenticationservice.security.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.filesmanagement.authenticationservice.dao.UserRepository;
import org.filesmanagement.authenticationservice.entities.User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserByUsername(String username){

        return userRepository.findByUsername(username);

    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
