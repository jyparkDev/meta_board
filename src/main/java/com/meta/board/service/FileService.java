package com.meta.board.service;

import com.meta.board.domain.file.FileMapper;
import com.meta.board.domain.file.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    /**
     * 파일 리스트 조회
     * @param boardId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    public List<FileResponse> findAllFileByPostId(final Long boardId) {
        return fileMapper.findAllByBoardId(boardId);
    }


    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
    public FileResponse findFileById(final Long id) {
        return fileMapper.findById(id);
    }


}
