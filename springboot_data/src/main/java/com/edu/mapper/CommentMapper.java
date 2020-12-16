package com.edu.mapper;
import com.edu.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper// 如果mapper注解过多用在启动类上加?@MapperScan("xxx")
//mapper是标识的作用mybtis接口文件   并且让springboot能够扫描生成mapper代理对象 存到容器中
public interface CommentMapper {

    @Select("SELECT * FROM t_comment WHERE id =#{id}")
    Comment findById(Integer id);
}
