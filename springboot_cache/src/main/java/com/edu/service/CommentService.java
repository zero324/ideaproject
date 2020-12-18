package com.edu.service;

import com.edu.pojo.Comment;
import com.edu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment findCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            Comment comment1 = comment.get();
            return comment1;
        }
        return null;
    }
}
