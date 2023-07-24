package com.meta.board.mapper;

import com.meta.board.domain.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardMapper {

    @Select("SELECT * FROM BOARD")
    List<BoardDto> findAll();
}
