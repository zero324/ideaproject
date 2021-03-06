package com.edu.controller;

import com.edu.pojo.Comment;
import com.edu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/findCommentById")
    public Comment findCommentById(Integer id) {
        Comment comment = commentService.findCommentById(id);
        return comment;
    }
    @RequestMapping(value = "/updateComment")
    public Comment updateComment(Comment comment ) {
        Comment comment1 = commentService.updateComment(comment);
        return comment1;
    }
    @RequestMapping(value = "/deleteComment")
    public String deleteComment(Integer id ) {
        commentService.deleteComment(id);
        return "deleteComment";
    }
}