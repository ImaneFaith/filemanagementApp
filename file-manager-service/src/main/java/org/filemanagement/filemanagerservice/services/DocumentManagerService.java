package org.filemanagement.filemanagerservice.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.filemanagerservice.config.DocumentManagerConfig;
import org.filemanagement.filemanagerservice.dto.DocumentDto;
import org.filemanagement.filemanagerservice.dto.DocumentDtoRequest;
import org.filemanagement.filemanagerservice.entities.Document;
import org.filemanagement.filemanagerservice.entities.File;
import org.filemanagement.filemanagerservice.exceptions.DocumentNotFoundException;
import org.filemanagement.filemanagerservice.exceptions.DocumentNotOwnerException;
import org.filemanagement.filemanagerservice.mappers.DocumentMapper;
import org.filemanagement.filemanagerservice.repositories.DocumentRepository;

import java.io.IOException;
import java.time.*;
import java.util.List;
import java.util.Objects;

import org.filemanagement.filemanagerservice.utils.PathBuilder;
import org.filemanagement.filemanagerservice.validators.UserValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.Doc;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DocumentManagerService {

    private final PathBuilder pathBuilder;
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final UserValidator userValidator;


    public Document getDocument(Long documentId){

        Document document = documentRepository.findById(documentId).orElseThrow(() ->
                new DocumentNotFoundException("document not found"));

        if(!userValidator.isOwner(document.getUsername()))
            throw new DocumentNotOwnerException("not owned document");

        return document;

    }

    public DocumentDto getDocumentDto(Long documentId){
        return documentMapper.mapToDocumentDto(getDocument(documentId));
    }

    public void createDocument(DocumentDtoRequest documentDtoRequest){

        Document document = documentMapper.mapToDocumentDtoRequest(documentDtoRequest);
        document.setUsername(userValidator.getUserName());
        documentRepository.save(document);
    }


    public void deleteDocument(Long documentId){
        Document document = getDocument(documentId);
        if(userValidator.isOwner(document.getUsername())){
            document.getFiles().clear();
            documentRepository.delete(document);
        }else{
             throw new DocumentNotOwnerException("not owned document");
        }

    }

    public List<DocumentDto> getDocuments(){
        return documentRepository.findAll().stream().map(documentMapper::mapToDocumentDto).toList();
    }







}
