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

   // @Select("SELECT * FROM BOARD ORDER BY MODIFIED_DATE DESC, ID DESC  LIMIT #{offset}, #{pageSize}")
    @Select("SELECT * FROM(SELECT @ROWNUM:=@ROWNUM+1 rnum ,T.id, T.title, T.view_num as viewNum,T.writer, T.create_date from board T, " +
            "(SELECT @ROWNUM:=0) R WHERE 1=1) list ORDER BY list.rnum DESC LIMIT #{offset}, #{pageSize}")
    List<BoardDto> findAll(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM BOARD WHERE id=#{id}")
    BoardDto findOne(@Param("id") Long id);

    @Select("SELECT PASSWORD FROM BOARD WHERE id=#{id}")
    String getBoardPasswd(@Param("id") Long id);

    @Select("SELECT * FROM BOARD WHERE ${list} LIKE CONCAT('%',#{keyword},'%') ORDER BY MODIFIED_DATE DESC, ID DESC LIMIT #{offset}, #{pageSize}")
    List<BoardDto> findByKeyword(@Param("keyword") String keyword, @Param("list") String list,@Param("offset") int offset, @Param("pageSize") int pageSize);


    @Update("UPDATE BOARD SET title=#{board.title}, content=#{board.content}, modified_date=now() WHERE id=#{id}")
    void update(@Param("id") Long id, @Param("board") Board board);

    @Delete("DELETE FROM BOARD WHERE id=#{id}")
    void delete(@Param("id") Long id);

    @Delete("TRUNCATE TABLE BOARD")
    void claer();

    @Select("SELECT COUNT(*) FROM BOARD")
    int count();


    @Select("SELECT count(*) FROM BOARD WHERE ${list} LIKE CONCAT('%',#{keyword},'%')")
    int keyWordCount(@Param("keyword") String keyword, @Param("list") String list);

    @Update("UPDATE BOARD SET VIEW_NUM = VIEW_NUM + 1 WHERE ID = #{id}")
    void addViewCount(@Param("id") Long id);
}
