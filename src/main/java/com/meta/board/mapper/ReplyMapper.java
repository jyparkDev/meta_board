package com.meta.board.mapper;

import com.meta.board.domain.reply.Reply;
import com.meta.board.domain.reply.ReplyMakeInfoDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ReplyMapper {
    @Update("UPDATE BOARD SET BOARD_GROUP_ORDER = BOARD_GROUP_ORDER + 1 WHERE BOARD_GROUP = #{info.boardGroup} AND BOARD_GROUP_ORDER > #{info.boardGroupOrder}")
    void updateBoardOrder(@Param("info") ReplyMakeInfoDto replyMakeInfoDto);

    @Insert("INSERT INTO BOARD(title,content,writer,passwd,create_date,modified_date,board_group, board_group_order, board_depth) " +
            "VALUES(#{reply.title},#{reply.content},#{reply.writer},#{reply.passwd},now(),now(), #{info.boardGroup}, #{info.boardGroupOrder} + 1,#{info.boardDepth} + 1)")
    void save(@Param("info") ReplyMakeInfoDto replyMakeInfoDto, Reply reply);
}
