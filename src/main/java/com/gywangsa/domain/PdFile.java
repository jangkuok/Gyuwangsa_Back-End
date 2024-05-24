package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PdFile {

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_nm")
    private String fileNm;

    @Column(name = "file_ord")
    private int fileOrd;

    public void changeFileOrd(int fileOrd) {
        this.fileOrd = fileOrd;
    }
}

