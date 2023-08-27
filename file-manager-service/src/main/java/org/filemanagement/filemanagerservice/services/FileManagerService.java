package org.filemanagement.filemanagerservice.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.filemanagement.filemanagerservice.communication.kafka.models.events.Event;
import org.filemanagement.filemanagerservice.communication.kafka.services.FilePublishService;
import org.filemanagement.filemanagerservice.dto.FileDtoRequest;
import org.filemanagement.filemanagerservice.entities.Document;
import org.filemanagement.filemanagerservice.entities.File;
import org.filemanagement.filemanagerservice.exceptions.DocumentNotOwnerException;
import org.filemanagement.filemanagerservice.exceptions.FileNotFoundException;
import org.filemanagement.filemanagerservice.repositories.FileRepository;
import org.filemanagement.filemanagerservice.utils.PathBuilder;
import org.filemanagement.filemanagerservice.validators.UserValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class FileManagerService {

    private final FileRepository fileRepository;
    private final DocumentManagerService documentManagerService;
    private final UserValidator userValidator;
    private final PathBuilder pathBuilder;
    private final FilePublishService filePublishService;




    public File getFile(Long fileId) {

        File file = fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));
        Document document = getDocumentOfFile(file);
        if (!userValidator.isOwner(document.getUsername())) {
            throw new DocumentNotOwnerException("not owned documment");
        }

        return file;
    }

    public void addFile(MultipartFile partFile, Long documentId) throws IOException {
        Document document = documentManagerService.getDocument(documentId);
        if (userValidator.isOwner(document.getUsername())) {
           @Valid File file = File.builder()
                    .fileType(partFile.getContentType())
                    .fileName(Objects.requireNonNull(partFile.getOriginalFilename()).split("\\.")[0])
                    .size(partFile.getSize())
                    .createdAt(LocalDateTime.now())
                    .content(partFile.getBytes())
                    .filePath(pathBuilder.generatePathFile(partFile.getOriginalFilename(), document.getDocumentName()))
                    .build();
            document.addFile(file);
            fileRepository.save(file);
            //publish event of file created to the user owner of the file
            filePublishService.publish(Event.builder().username(document.getUsername()).data(file.getFileName()).build());
        } else {
            throw new DocumentNotOwnerException("not owned documment");
        }

    }

    public void deleteFile(Long fileId) {
        File file = getFile(fileId);
        Document document = getDocumentOfFile(file);

        if (!userValidator.isOwner(document.getUsername())) {
            throw new DocumentNotOwnerException("not owned documment");
        }
        document.removeFile(file);
        fileRepository.delete(file);
    }

    public File readFile(Long fileId) {
        File file = getFile(fileId);
        Document document = documentManagerService.getDocument(file.getDocument().getDocumentId());

        if (userValidator.isOwner(document.getUsername())) {
            return file;
        } else {
            throw new DocumentNotOwnerException("not owned documment");
        }

    }

    public void updateFile(Long fileId, FileDtoRequest fileDtoRequest) {

        File file = getFile(fileId);
        file.setFileName(fileDtoRequest.getNewName());
        fileRepository.save(file);

    }

    private Document getDocumentOfFile(File file) {
        return documentManagerService.getDocument(file.getDocument().getDocumentId());
    }
}
