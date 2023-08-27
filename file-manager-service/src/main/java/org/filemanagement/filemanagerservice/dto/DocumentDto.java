package org.filemanagement.filemanagerservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.filemanagement.filemanagerservice.entities.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDto {


    @NotBlank
    private Long documentId;
    private String  documentName;
    private String username;
    private List<File> files;
}
