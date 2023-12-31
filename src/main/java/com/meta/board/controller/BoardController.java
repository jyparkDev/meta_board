package com.meta.board.controller;

import com.meta.board.domain.*;
import com.meta.board.domain.board.BoardDto;
import com.meta.board.domain.board.BoardUpdateDto;
import com.meta.board.domain.file.FileRequest;
import com.meta.board.domain.file.FileResponse;
import com.meta.board.domain.file.FileUtils;
import com.meta.board.model.Board;
import com.meta.board.service.BoardService;
import com.meta.board.service.CommentService;
import com.meta.board.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    /** 전체 조회 Controller */
    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Condition condition, Model model){
        String sort = condition.getSort();

        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;

        model.addAttribute("sort" , sort);

        if (sort.equals("old_date")){
            condition.setSort("create_date");
            condition.setDir("DESC");
        }

        int totalCnt = boardService.getCount(condition.getKeyword(),condition.getList());
        PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);

        List<BoardDto> boardList = boardService.findAll((page - 1) * pageSize ,pageSize, condition);


        if(sort.equals("old_date")){
            condition.setSort("old_date");
        }

        model.addAttribute("boards", boardList);
        model.addAttribute("ph" , pageHandler);
        model.addAttribute("condition",condition);

        return "/board/list";
    }

    /** 상세 조회 Controller */
    @GetMapping("/view")
    public String view(Long id,
                       int page, int pageSize,
                       Condition condition, Model model, boolean scroll){

        BoardDto board = boardService.findOne(id);

        Map<String, Object> result = commentService.searchList(id, 1,  new HashMap<String, Object>());
        model.addAttribute("result", result);
        model.addAttribute("board",board);
        model.addAttribute("page",page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("condition",condition);
        model.addAttribute("scroll",scroll);

        return "/board/detail";
    }

    /** 글쓰기 Form 이동 Controller */
    @GetMapping("/write")
    public String writeForm(){
        return "/board/writeForm";
    }

    /** 글작성 Controller */
    @PostMapping("/write")
    public String write(@ModelAttribute Board board) throws IOException {

        boardService.join(board);

        return "redirect:/board/list?page=1&pageSize=10&sort=create_date";
    }

    /** 글수정Form Controller */
    @GetMapping("/update")
    public String updateForm(Long id,Model model){
        BoardDto updateBoard = boardService.findOne(id);
        model.addAttribute("board",updateBoard);
        return "/board/updateForm";
    }

    /** 글수정 Controller */
    @PostMapping("/update")
    public String update(@ModelAttribute BoardUpdateDto board) throws IOException {
        log.info("id : {}" , board.getRemoveFileIds());
        boardService.updateBoard(board.getId(),board);
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(board.getFiles());

        fileService.saveFiles(board.getId(),uploadFiles);

        List<FileResponse> deleteFiles = fileService.findAllFileByIds(board.getRemoveFileIds());

        fileUtils.deleteFiles(deleteFiles);

        fileService.deleteAllFileByIds(board.getRemoveFileIds());

        return "redirect:/";
    }

    /** 글삭제 Controller */
    @GetMapping("/remove")
    public String remove(@RequestParam("id") Long id, @RequestParam("check") boolean check,HttpServletResponse res){
        if (!check){
            res.setStatus(HttpStatus.NOT_FOUND.value());
            return "/error/404";
        }
        List<FileResponse> fileResponses = fileService.deleteFileNum(id);
        if (fileResponses.size() > 0){
            fileUtils.deleteFiles(fileResponses);
            boardService.deleteBoard(id);
            List<Long> deleteFileIds = new ArrayList<>();
            for (FileResponse f : fileResponses) {
                deleteFileIds.add(f.getId());
            }
            fileService.deleteAllFileByIds(deleteFileIds);
        }

        return "redirect:/";
    }

    /** 비밀번화 확인 Controller */
    @PostMapping("/passwd-check")
    @ResponseBody
    public Map<String , String> passwordCheck(@RequestBody PassWordRequestDto passWordRequestDto, HttpServletResponse res){
        Map<String , String> result = new HashMap<>();
        if(!boardService.validPassWord(passWordRequestDto.getId(), passWordRequestDto.getPasswd())){
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        result.put("status","OK");
        return result;
    }
}
