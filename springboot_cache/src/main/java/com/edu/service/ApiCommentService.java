package com.edu.service;

import com.edu.pojo.Comment;
import com.edu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ApiCommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    //用redis api的方式进行缓存,先去缓存中找 有返回 没有查数据库
    public Comment findCommentById(Integer id) {
        Object o = redisTemplate.opsForValue().get("comment_"+id);
        if(o!=null){
            return (Comment)o;
        }else {
            Optional<Comment> comment = commentRepository.findById(id);
            if (comment.isPresent()) {
                Comment comment1 = comment.get();
                //存入redis中 并且设置有效时间
                redisTemplate.opsForValue().set("comment_"+id,comment1,1, TimeUnit.DAYS);
                return comment1;
            }
        }
        return null;
    }

    public Comment updateComment(Comment comment) {
        Optional<Comment> opt = commentRepository.findById(comment.getId());
        Comment comment1 = opt.get();
        comment1.setAuthor(comment.getAuthor());
        commentRepository.updateComment(comment);
        redisTemplate.opsForValue().set("comment_"+comment.getId(),comment1,1, TimeUnit.DAYS);

        return comment1;
    }
    @CacheEvict(cacheNames = "comment",key="#id")
    public void deleteComment(Integer id){
        commentRepository.deleteById(id);
        redisTemplate.delete("comment_"+id);
    }

}
