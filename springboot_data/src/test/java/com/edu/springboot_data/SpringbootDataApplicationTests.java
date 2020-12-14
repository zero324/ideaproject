package com.edu.springboot_data;

import com.edu.mapper.ArticleMapper;
import com.edu.mapper.CommentDao;
import com.edu.mapper.CommentMapper;
import com.edu.pojo.Article;
import com.edu.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootDataApplicationTests {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentDao commentDao;

    @Test
    void contextLoads() {
        Comment comment = commentMapper.findById(1);
        System.out.println(comment);
    }
    @Test
    void testArticleMapper() {
        Article article = articleMapper.selectArticle(1);
        System.out.println(article);
    }

    @Test
    void testDataJpa() {
        Comment one = commentDao.getOne(1);
        System.out.println(one);
    }

}
