package com.edu.springboot_data;

import com.edu.mapper.ArticleMapper;
import com.edu.mapper.CommentDao;
import com.edu.mapper.CommentMapper;
import com.edu.mapper.PersonRepository;
import com.edu.pojo.Address;
import com.edu.pojo.Article;
import com.edu.pojo.Comment;
import com.edu.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootDataApplicationTests {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ApplicationContext context;

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
    @Transactional
    void testDataJpa() {
        Comment one = commentDao.getOne(1);
        System.out.println(one);
    }

    @Test
    public void testXXXX() {

        Object dataSource = context.getBean("dataSource");
        System.out.println(dataSource);

    }


    @Autowired
    private PersonRepository repository;

    @Test
    public void savePerson() {
        Person person = new Person();
        person.setFirstname("张");
        person.setLastname("三");
        Address address = new Address();
        address.setCity("北京");
        address.setCountry("中国");
        person.setAddress(address);
        //向redis中添加数据
        Person save = repository.save(person);
    }

    @Test
    public void selectPerson() {
        List<Person> list = (List<Person>) repository.findByAddress_City("北京");
        for (Person person : list) {
            System.out.println(person);
        }
    }
}