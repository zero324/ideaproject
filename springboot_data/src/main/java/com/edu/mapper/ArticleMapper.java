package com.edu.mapper;

import com.edu.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
     Article selectArticle(Integer id);
}
