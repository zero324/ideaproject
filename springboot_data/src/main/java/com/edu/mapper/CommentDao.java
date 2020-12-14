package com.edu.mapper;

import com.edu.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentDao extends JpaRepository<Comment,Integer>, JpaSpecificationExecutor<Comment> {
}
