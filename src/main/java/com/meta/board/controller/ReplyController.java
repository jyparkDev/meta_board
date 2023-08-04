package com.meta.board.controller;

import com.meta.board.domain.reply.Reply;
import com.meta.board.service.BoardService;
import com.meta.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reply")
public class ReplyController {

    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("/write")
    public String replyForm(int id, Model model){
        String title = boardService.getTitle((long) id);

        model.addAttribute("id", id);
        model.addAttribute("title", title);

        return "/reply/writeForm";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Reply reply){
        replyService.join(reply);
        return "redirect:/";
    }
}
