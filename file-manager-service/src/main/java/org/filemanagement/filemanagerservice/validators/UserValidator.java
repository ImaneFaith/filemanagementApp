package org.filemanagement.filemanagerservice.validators;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@AllArgsConstructor
public class UserValidator {

    @Autowired
    SecurityContext ctx;


    public String getUserName(){
        return ctx.getAuthentication().getName();
    }

    public boolean isOwner(String thatUsername){
        String thisUsername = getUserName();
        return thisUsername!= null && thisUsername.equals(thatUsername);
    }
}
