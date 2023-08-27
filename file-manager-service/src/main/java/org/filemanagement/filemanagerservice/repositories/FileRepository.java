package org.filemanagement.filemanagerservice.repositories;

import org.filemanagement.filemanagerservice.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
