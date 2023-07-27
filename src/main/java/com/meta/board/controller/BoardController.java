package com.meta.board.controller;

import com.meta.board.domain.*;
import com.meta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * 글쓰기 Form 이동 Controller
     */
    @GetMapping("/board/write")
    public String writeForm(){
        return "/board/writeForm";
    }

    /**
     * 글작성 Controller
     * @param board
     * @return
     */
    @PostMapping("/board/write")
    public String write(@ModelAttribute Board board){
        String title = board.getTitle();
        int length = title.getBytes().length;
        log.info("lengh : {}",length);
        boardService.join(board);
        return "redirect:/board/list?page=1&pageSize=10&sort=create_date";
    }

    /**
     * 글수정Form Controller
     */
    @GetMapping("/board/update")
    public String updateForm(Long id,Model model){
        BoardDto updateBoard = boardService.findOne(id);
        model.addAttribute("board",updateBoard);
        return "/board/updateForm";
    }

    /**
     * 글수정 Controller
     */
    @PostMapping("/board/update")
    public String update(@ModelAttribute BoardUpdateDto board){
        boardService.updateBoard(board.getId(),board);
        return "redirect:/";
    }

    /**
     * 글삭제 Controller
     */
    @GetMapping("/board/remove")
    public String remove(@RequestParam("id") Long id, @RequestParam("check") boolean check,HttpServletResponse res){
        if (!check){
            res.setStatus(HttpStatus.NOT_FOUND.value());
            return "/error/404";
        }
        boardService.deleteBoard(id);
        return "redirect:/";
    }

    /**
     * 비밀번화 확인 Controller
     */
    @PostMapping("/board/passwd-check")
    @ResponseBody
    public Map<String , String> passwordCheck(@RequestBody PassWordRequestDto passWordRequestDto, HttpServletResponse res){
        Map<String , String> result = new HashMap<>();
        log.info("log : {},{}",passWordRequestDto.getPassword(),passWordRequestDto.getId());
        if(!boardService.validPassWord(passWordRequestDto.getId(), passWordRequestDto.getPassword())){
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        result.put("status","OK");
        return result;
    }
}
