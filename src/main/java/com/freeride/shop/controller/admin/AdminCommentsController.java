package com.freeride.shop.controller.admin;

import com.freeride.shop.entity.Comment;
import com.freeride.shop.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/comments")
public class AdminCommentsController {

    private CommentService commentService;

    public AdminCommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String getAllComments(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<Comment> comments = commentService.getAllComments(page);

        model.addAttribute("allComments", "");
        model.addAttribute("comments", comments.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", comments.getTotalPages());
        return "pages/admin-page";
    }
}
