package com.edu.mapper;
import com.edu.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper// 如果mapper注解过多用在启动类上加?@MapperScan("xxx")
public interface CommentMapper {

    @Select("SELECT * FROM t_comment WHERE id =#{id}")
    Comment findById(Integer id);
}
