package com.edu.service;

import com.edu.pojo.Comment;
import com.edu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Cacheable(cacheNames = "comment")
    public Comment findCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            Comment comment1 = comment.get();
            return comment1;
        }
        return null;
    }
    @CachePut(cacheNames = "comment",key="#result.id")
    public Comment updateComment(Comment comment) {
        Optional<Comment> opt = commentRepository.findById(comment.getId());
        Comment comment1 = opt.get();
        comment1.setAuthor(comment.getAuthor());
        commentRepository.updateComment(comment);

        return comment1;
    }
    @CacheEvict(cacheNames = "comment",key="#id")
    public void deleteComment(Integer id){
        commentRepository.deleteById(id);
    }

}
