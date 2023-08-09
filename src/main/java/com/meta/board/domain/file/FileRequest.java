package com.meta.board.domain.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileRequest {

    private Long id;
    private Long boardId;
    private String originalName;
    private String saveName;
    private long size;

    @Builder
    public FileRequest(String originalName, String saveName, long size) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.size = size;
    }

    public void setBoardId(Long boardId){
        this.boardId = boardId;
    }
}
