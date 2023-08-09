package com.meta.board.domain.file;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtils {

    private final static String uploadPath = Paths.get("C:","file","upload-file").toString();

    /**
     * 다중 파일 업로드
     * @param multipartFiles - 파일 객체 List
     * @return DB에 저장할 파일 정보 List
     */
    public static List<FileRequest> uploadFiles(final List<MultipartFile> multipartFiles) throws IOException {
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
    private static FileRequest uploadFile(MultipartFile multipartFile) throws IOException {

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

    private static String getUploadPath() {
        return makeDirectories(uploadPath);
    }
    private static String getUploadPath(String addPath) {

        return makeDirectories(uploadPath + File.separator + addPath);
    }

    private static String generateSaveFileName(String originalFilename) {

        String uuid = UUID.randomUUID().toString();
        String extractExt = StringUtils.getFilenameExtension(originalFilename);
        return uuid + "." + extractExt;
    }

    private static String makeDirectories(String path) {
        File dir = new File(path);
        if(dir.exists() == false){
            dir.mkdirs();
        }
        return dir.getPath();
    }


}
