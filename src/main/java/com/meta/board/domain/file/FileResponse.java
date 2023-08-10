package com.meta.board.domain.file;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class FileResponse {
    private Long id;
    private Long boardId;
    private String originalName;
    private String saveName;
    private long size;
    private Boolean deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;
}
