package com.meta.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class BoardUpdateDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private List<Long> removeFileIds = new ArrayList<>(); // 삭제할 첨부파일 id List
    private List<MultipartFile> files = new ArrayList<>();

}
