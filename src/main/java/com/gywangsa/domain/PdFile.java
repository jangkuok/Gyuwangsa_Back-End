package com.gywangsa.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdFile {
    private long fileId;
    private String filePath;
    private String fileNm;
    private String fileType;
    private int ord;

}

