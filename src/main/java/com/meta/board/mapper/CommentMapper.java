package com.meta.board.mapper;

import com.meta.board.domain.comment.CommentRequestDto;
import com.meta.board.domain.comment.CommentResponseDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    @Insert("INSERT INTO COMMENTS(WRITER,PASSWD,CONTENT,CREATE_DATE,MODIFIED_DATE,BOARD_ID) VALUES(#{dto.writer}, #{dto.passwd}, #{dto.content}, now(), now(), #{dto.id})")
    void save(@Param("dto") CommentRequestDto commentRequestDto);

    @Select("SELECT * FROM COMMENTS WHERE BOARD_ID = #{id} ORDER BY ID DESC LIMIT #{page}, #{pageSize}")
    List<CommentResponseDto> findAllByBoardId(@Param("id") Long id, @Param("page") int page, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM COMMENTS WHERE BOARD_ID = #{id}")
    int count(Long id);

    @Select("SELECT PASSWD FROM COMMENTS WHERE ID = #{id}")
    String findPassword(Long id);

    @Delete("DELETE FROM COMMENTS WHERE ID = #{id}")
    void remove(Long id);
}
