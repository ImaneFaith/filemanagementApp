package org.filemanagement.filemanagerservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.*;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "files", uniqueConstraints = { @UniqueConstraint(columnNames = {"file_name","document_id"})})
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_file")
    private Long idFile;

    @Column(name = "file_name")
    @Size(min = 2,max = 10)
    private String fileName;

    @Column(name = "file_type")
    private String fileType;


    private long size;

    @Column(name = "file_path")
    private String filePath;

    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonBackReference
    @JsonIgnore
    private Document document;


}
