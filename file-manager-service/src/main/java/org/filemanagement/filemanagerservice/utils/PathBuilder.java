package org.filemanagement.filemanagerservice.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.filemanagement.filemanagerservice.config.DocumentManagerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class PathBuilder {


    private final DocumentManagerConfig documentManagerConfig;


    @PostConstruct
    String getDirectory(){

        return documentManagerConfig.getUploadDirectory();
    }

    private UriComponentsBuilder directory(){
        return host().path(getDirectory());
    }
    private UriComponentsBuilder host(){
        return scheme().host("org.filemanagement.com");
    }
    private UriComponentsBuilder scheme(){
        return UriComponentsBuilder.newInstance().scheme("http");
    }

    public String generatePathFile(String fileName,String documentName){
        return directory().path(documentName).path("/").path(fileName).build().toString();

    }


}
