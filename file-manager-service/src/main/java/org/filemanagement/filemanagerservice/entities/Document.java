package org.filemanagement.filemanagerservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "documents", uniqueConstraints = { @UniqueConstraint(columnNames = {"document_name","username"})})

public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "document_name")
    @NotBlank
    private String documentName;


    private String username;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<File> files = new ArrayList<>();

    public void addFile(File file){
        file.setDocument(this);
        this.getFiles().add(file);
    }

    public void removeFile(File file){
        file.setDocument(null);
        this.getFiles().remove(file);
    }

}
