package com.meta.board.mapper;

import com.meta.board.domain.BoardBean;
import com.meta.board.domain.BoardDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface BoardMapper {
    @Insert("INSERT INTO BOARD(title,content,writer,passwd,create_date,modified_date) " +
            "VALUES(#{board.title},#{board.content},#{board.writer},#{board.passwd},now(),now())")
    void save(@Param("board") BoardBean boardBean);

    @Select("SELECT * FROM BOARD")
    List<BoardDto> findAll();

    @Select("SELECT * FROM BOARD WHERE id=#{id}")
    BoardDto findOne(@Param("id") Long id);

    @Update("UPDATE BOARD SET title=#{board.title}, content=#{board.content} WHERE id=#{id}")
    void update(@Param("id") Long id, @Param("board") BoardBean boardBean);

    @Delete("DELETE FROM BOARD WHERE id=#{id}")
    void delete(@Param("id") Long id);

    @Delete("TRUNCATE TABLE BOARD")
    void claer();
}
