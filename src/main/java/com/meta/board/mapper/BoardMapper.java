package com.meta.board.mapper;

import com.meta.board.domain.Board;
import com.meta.board.domain.BoardDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardMapper {
    @Insert("INSERT INTO BOARD(title,content,writer,password,create_date,modified_date) " +
            "VALUES(#{board.title},#{board.content},#{board.writer},#{board.passwd},now(),now())")
    void save(@Param("board") Board board);

    @Select("SELECT * FROM BOARD ORDER BY MODIFIED_DATE DESC, ID DESC  LIMIT #{offset}, #{pageSize}")
    List<BoardDto> findAll(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM BOARD WHERE id=#{id}")
    BoardDto findOne(@Param("id") Long id);

    @Select("SELECT PASSWORD FROM BOARD WHERE id=#{id}")
    String getBoardPasswd(@Param("id") Long id);

    @Select("SELECT * FROM BOARD WHERE ${list} LIKE CONCAT('%',#{keyword},'%') ORDER BY MODIFIED_DATE DESC, ID DESC LIMIT 10")
    List<BoardDto> findByKeyword(@Param("keyword") String keyword, @Param("list") String list);


    @Update("UPDATE BOARD SET title=#{board.title}, content=#{board.content}, modified_date=now() WHERE id=#{id}")
    void update(@Param("id") Long id, @Param("board") Board board);

    @Delete("DELETE FROM BOARD WHERE id=#{id}")
    void delete(@Param("id") Long id);

    @Delete("TRUNCATE TABLE BOARD")
    void claer();

    @Select("SELECT COUNT(*) FROM BOARD")
    int count();
}
