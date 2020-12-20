package com.edu.repository;

import com.edu.pojo.Comment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Transactional
    @Modifying
    @Query(value="update t_comment c set c.author = :#{#comment.author} where  c.id=:#{#comment.id}",nativeQuery=true )
     int updateComment(Comment comment);
}
