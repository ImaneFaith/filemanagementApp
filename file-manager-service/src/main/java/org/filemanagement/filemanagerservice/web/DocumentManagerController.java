package org.filemanagement.filemanagerservice.web;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.filemanagerservice.dto.DocumentDto;
import org.filemanagement.filemanagerservice.dto.DocumentDtoRequest;
import org.filemanagement.filemanagerservice.models.HttpResponse;
import org.filemanagement.filemanagerservice.services.DocumentManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/documents")
@Tag(name = "Document Manager API")

public class DocumentManagerController {

    private final DocumentManagerService documentManagerService;

    @PostMapping
    public ResponseEntity<HttpResponse> createDocument(@RequestBody @Valid DocumentDtoRequest documentDtoRequest, HttpServletRequest request){

        documentManagerService.createDocument(documentDtoRequest);
        return ResponseEntity.ok(HttpResponse.builder().message("document created succefully").status(HttpStatus.CREATED).build());
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<HttpResponse> deleteDocument(@PathVariable Long documentId){
        documentManagerService.deleteDocument(documentId);

        return ResponseEntity.ok(HttpResponse.builder().message("Document Removed Successfully").status(HttpStatus.OK).build());
    }
    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable Long documentId){
        DocumentDto documentDto = documentManagerService.getDocumentDto(documentId);
        return ResponseEntity.ok(documentDto);
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getAllDocuments(){
        return ResponseEntity.ok(documentManagerService.getDocuments());
    }



}
