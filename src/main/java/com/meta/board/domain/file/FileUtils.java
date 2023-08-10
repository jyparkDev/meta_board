package com.meta.board.domain.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {

    private final String uploadPath = Paths.get("C:","file","upload-file").toString();

    /**
     * 다중 파일 업로드
     * @param multipartFiles - 파일 객체 List
     * @return DB에 저장할 파일 정보 List
     */
    public List<FileRequest> uploadFiles(final List<MultipartFile> multipartFiles) throws IOException {
        List<FileRequest> files = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()){
                continue;
            }
            files.add(uploadFile(multipartFile));
        }

        return files;
    }

    /**
     * 단일 파일 업로드
     * @param multipartFile - 파일 객체
     * @return DB에 저장할 파일 정보
     */
    private FileRequest uploadFile(MultipartFile multipartFile) throws IOException {

        String saveName = generateSaveFileName(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String uploadPath = getUploadPath(today) + File.separator + saveName;

        File file = new File(uploadPath);

        multipartFile.transferTo(file);

        return FileRequest.builder()
                .originalName(multipartFile.getOriginalFilename())
                .saveName(saveName)
                .size(multipartFile.getSize())
                .build();

    }

    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }
    private String getUploadPath(String addPath) {

        return makeDirectories(uploadPath + File.separator + addPath);
    }

    private String generateSaveFileName(String originalFilename) {

        String uuid = UUID.randomUUID().toString();
        String extractExt = StringUtils.getFilenameExtension(originalFilename);
        return uuid + "." + extractExt;
    }

    private String makeDirectories(String path) {
        File dir = new File(path);
        if(dir.exists() == false){
            dir.mkdirs();
        }
        return dir.getPath();
    }



    /**
     * 다운로드할 첨부파일(리소스) 조회 (as Resource)
     * @param file - 첨부파일 상세정보
     * @return 첨부파일(리소스)
     */
    public Resource readFileAsResource(final FileResponse file) {
        String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String filename = file.getSaveName();
        Path filePath = Paths.get(uploadPath, uploadedDate, filename);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()  || !resource.isFile()) {
                throw new RuntimeException("file not found : " + filePath.toString());
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("file not found : " + filePath.toString());
        }
    }


}
