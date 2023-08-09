package com.meta.board.domain.file;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    /**
     * 파일 정보 저장
     */
    void saveAll(List<FileRequest> files) ;
}
