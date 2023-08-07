package com.meta.board.mapper;

import com.meta.board.domain.board.Board;
import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardUpdateDto;
import com.meta.board.domain.Condition;
import com.meta.board.domain.reply.ReplyMakeInfoDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardMapper {
    /* 게시글 등록 QUERY*/
    @Insert("INSERT INTO BOARD(title,content,writer,passwd,create_date,modified_date) " +
            "VALUES(#{board.title},#{board.content},#{board.writer},#{board.passwd},now(),now())")
    void save(@Param("board") Board board);

    /* 게시글 수정 QUERY*/
    @Update("UPDATE BOARD SET title=#{board.title}, content=#{board.content}, writer=#{board.writer}, modified_date=now() WHERE id=#{id}")
    void update(@Param("id") Long id, @Param("board") BoardUpdateDto board);

    /* 조회카운드 증가 QUERY*/
    @Update("UPDATE BOARD SET VIEW_NUM = VIEW_NUM + 1 WHERE ID = #{id}")
    void addViewCount(@Param("id") Long id);

    @Update("UPDATE BOARD SET BOARD_GROUP = #{board_group} WHERE ID = #{id}")
    void updateBoardGroup(Long id, Long board_group);

    /* 게시글 삭제 QUERY */
    @Update("UPDATE BOARD SET TITLE='삭제된 게시글입니다', CONTENT='작성자에 의해 삭제된 글입니다.', EXIST = 0 WHERE id=#{id}")
    void delete(@Param("id") Long id);

    /* TEST 용도 QUERY*/
    @Delete("TRUNCATE TABLE BOARD")
    void claer();

    /* 게시글 목록 조회 QUERY */
    //    order by board_group desc, board_group_order asc
//    @Select("SELECT * FROM(SELECT @ROWNUM:=@ROWNUM+1 rnum ,B.id, B.title, B.view_num as viewNum,B.writer, B.create_date, " +
//            "(select count(*) from comments c where B.id = c.board_id) as comment_count from board B, " +
//            "(SELECT @ROWNUM:=0) R WHERE 1=1 and ${condition.list} like concat('%',#{condition.keyword},'%') " +
//            "order by ${condition.sort} ${condition.dir}, id ${condition.dir}) list ORDER BY list.rnum DESC limit #{offset}, #{pageSize}")
    @Select("SELECT * FROM(SELECT @ROWNUM:=@ROWNUM+1 rnum ,B.id, B.title, B.view_num as viewNum,B.writer, B.create_date, B.board_depth, " +
            "(select count(*) from comments c where B.id = c.board_id) as comment_count from board B, " +
            "(SELECT @ROWNUM:=0) R WHERE 1=1 and ${condition.list} like concat('%',#{condition.keyword},'%') " +
            "order by BOARD_GROUP ${condition.dir}, BOARD_GROUP_ORDER desc) list ORDER BY list.rnum DESC limit #{offset}, #{pageSize}")
    List<BoardDto> findAll(@Param("offset") int offset, @Param("pageSize") int pageSize, Condition condition);

    /* 단일 게시글 조회 QUERY */
    @Select("SELECT * FROM BOARD WHERE id=#{id}")
    BoardDto findOne(@Param("id") Long id);

    /* 비밀번호 조회 QUERY */
    @Select("SELECT PASSWD FROM BOARD WHERE id=#{id}")
    String getBoardPasswd(@Param("id") Long id);

    /* 게시글 수 조회 QUERY */
    @Select("SELECT COUNT(*) FROM BOARD WHERE ${list} LIKE CONCAT('%',#{keyword},'%')")
    int count(String keyword, String list);

    /* 게시글 마지막 식별번호 조회 */
    @Select("SELECT ID FROM BOARD ORDER BY ID DESC LIMIT 1")
    Long findLastBoardId();

    @Select("SELECT TITLE FROM BOARD WHERE ID = #{id}")
    String findTitle(Long id);

    @Select("SELECT BOARD_GROUP, BOARD_GROUP_ORDER, BOARD_DEPTH FROM BOARD WHERE ID = #{id}")
    ReplyMakeInfoDto findInfoForMakeReply(Long id);
}
