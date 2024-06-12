package com.gywangsa.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void initState(){
        File folder = new File(uploadPath);

        if(!folder.exists()){
            folder.mkdir();
        }

        uploadPath = folder.getAbsolutePath();

        log.info("=====================================");
        log.info("==============>파일업로드 : " + uploadPath);
        log.info("=====================================");
    }

    //업로드
    public List<String> saveFile(List<MultipartFile> files) throws RuntimeException {

        if(files == null || files.size() == 0){
            return null;
        }

        List<String> uploadNames = new ArrayList<>();
        for(MultipartFile file: files){

            String saveName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
            Path savaPath = Paths.get(uploadPath,saveName);
            try {
                //원본 파일
                Files.copy(file.getInputStream(),savaPath);
                uploadNames.add(saveName);

                //썸네일 파일
                String contentType = file.getContentType();
                if(contentType != null || contentType.startsWith("image")){
                    Path thumbnailPath = Paths.get(uploadPath, "s_" + saveName);
                    Thumbnails.of(savaPath.toFile()).size(180,200).toFile(thumbnailPath.toFile());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uploadNames;
    }

    //파일 조회
    public ResponseEntity<Resource> selectByFile(String fileName){
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);

        if(!resource.isReadable()){
            resource = new FileSystemResource(uploadPath+File.separator+"");
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    //파일 삭제
    public void deleteFiles(List<String> fileNames){
        if(fileNames == null || fileNames.size() == 0){
            return;
        }

        fileNames.forEach(fileName ->{
            //썸네일 삭제
            String thumbnailFileName = "s_"+fileName;
            Path thumbnailPath = Paths.get(uploadPath,thumbnailFileName);
            Path filePath = Paths.get(uploadPath, fileName);
            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
