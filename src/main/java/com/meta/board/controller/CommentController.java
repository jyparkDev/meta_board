package com.meta.board.controller;

import com.meta.board.domain.PassWordRequestDto;
import com.meta.board.domain.comment.CommentRequestDto;
import com.meta.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @PostMapping("write")
    public Map<String, Object> write(@RequestBody CommentRequestDto commentRequestDto){
        Map<String, Object> model = new HashMap<>();
        commentService.join(commentRequestDto,model);
        return model;
    }

    @GetMapping("/search")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam("page") int page, @RequestParam("id") Long id){
        Map<String, Object> model = new HashMap<>();

        commentService.searchList(id,page,model);
        return model;
    }

    @PostMapping("/passwdCheck")
    @ResponseBody
    public Map<String, String> passwordCheck(@RequestBody PassWordRequestDto passWordRequestDto){
        Map<String,String> model = new HashMap<>();
        if(!commentService.passwdCheck(passWordRequestDto.getId(),passWordRequestDto.getPasswd())){
            model.put("msg","fail");
            return model;
        }
        model.put("msg","ok");
        return model;
    }

    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> remove(@RequestParam("page") int page, @RequestParam("id") Long id,@RequestParam("c_id") Long commentId){

        Map<String, Object> model = new HashMap<>();

        commentService.removeComment(id,page,commentId,model);

        return model;
    }
}

