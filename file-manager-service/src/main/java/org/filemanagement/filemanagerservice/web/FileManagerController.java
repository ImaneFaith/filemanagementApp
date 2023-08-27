package org.filemanagement.filemanagerservice.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.filemanagement.filemanagerservice.dto.FileDtoRequest;
import org.filemanagement.filemanagerservice.entities.File;
import org.filemanagement.filemanagerservice.models.HttpResponse;
import org.filemanagement.filemanagerservice.services.FileManagerService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
@Tag(name = "File Manager API")
public class FileManagerController {

    private final FileManagerService fileManagerService;

    @Operation(
            description = "ADD file to document",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200 OK"
                    ),
                    @ApiResponse(
                            description = "FAILED",
                            responseCode = "404 NOT FOUND : DocumentNotFound"
                    ),
                    @ApiResponse(
                            description = "FAILED",
                            responseCode = "403 FORBIDDEN : DocumentNotOwned"
                    ),
                    @ApiResponse(
                            description = "FAILED",
                            responseCode = "404 BAD REQUEST : FileSizeLimitExceededException"
                    ),
            }
    )
    @PostMapping(value = "/{documentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpResponse> addFile(@RequestPart  MultipartFile file, @PathVariable Long documentId) throws IOException {
        fileManagerService.addFile(file, documentId);
        return ResponseEntity.ok(HttpResponse.builder().message("File added to document successfully").status(HttpStatus.OK).build());
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<HttpResponse> deleteFile(@PathVariable Long fileId){
        fileManagerService.deleteFile(fileId);
        return ResponseEntity.ok(HttpResponse.builder().message("File deleted successfully").status(HttpStatus.OK).build());
    }

    @Operation(
            description = "GET file content from DB",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200 OK"
                    ),
                    @ApiResponse(
                            description = "FAILED",
                            responseCode = "404 NOT FOUND"
                    ),
                    @ApiResponse(
                            description = "FAILED",
                            responseCode = "403 FORBIDDEN : DocumentNotOwned"
                    ),

            }
    )
    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> readFile(@PathVariable Long fileId){
        File file = fileManagerService.readFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType())).body(new ByteArrayResource(file.getContent()));
    }

    @Operation(
            description = "GET file metadata from DB",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200 OK"
                    ),
                    @ApiResponse(
                            description = "FAILED",
                            responseCode = "404 NOT FOUND"
                    ),
                    @ApiResponse(
                            description = "FAILED",
                            responseCode = "403 FORBIDDEN : DocumentNotOwned"
                    ),

            }
    )
    @GetMapping("/{fileId}/metadata")
    public ResponseEntity<File> getFile(@PathVariable Long fileId) {
        File file = fileManagerService.getFile(fileId);
        return ResponseEntity.ok(file);
    }


    @PutMapping(value = "/{fileId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> updateFile(@PathVariable Long fileId, @RequestBody @Valid FileDtoRequest fileDtoRequest){
        fileManagerService.updateFile(fileId, fileDtoRequest);
        return ResponseEntity.ok(HttpResponse.builder().message("file updated succefully").status(HttpStatus.OK).build());

    }

}
