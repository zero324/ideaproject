package com.edu.controller;

import com.edu.pojo.Comment;
import com.edu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/findCommentById")
    public Comment findCommentById(Integer id) {
        Comment comment = commentService.findCommentById(id);
        return comment;
    }
}