package org.filemanagement.filemanagerservice.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.filemanagement.filemanagerservice.dto.DocumentDto;
import org.filemanagement.filemanagerservice.dto.DocumentDtoRequest;
import org.filemanagement.filemanagerservice.entities.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public Document mapToDocument(DocumentDto documentDto){

        return Document.builder()
                .documentName(documentDto.getDocumentName())
                .files(documentDto.getFiles())
                .username(documentDto.getUsername())
                .build();
    }

    public Document mapToDocumentDtoRequest(DocumentDtoRequest documentDtoRequest){

        return Document.builder()
                .documentName(documentDtoRequest.getDocumentName())
                .build();
    }

    public DocumentDto mapToDocumentDto(Document document){

        return DocumentDto.builder()
                .documentId(document.getDocumentId())
                .documentName(document.getDocumentName())
                .files(document.getFiles())
                .username(document.getUsername())
                .build();
    }
}
