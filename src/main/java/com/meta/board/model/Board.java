package com.meta.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Setter
@ToString
public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String passwd;
    private List<MultipartFile> files = new ArrayList<>();
    public void passwordEncoding(String passwd){
        this.passwd = passwd;
    }

}
