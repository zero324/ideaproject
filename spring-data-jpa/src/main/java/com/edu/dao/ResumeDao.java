package com.edu.dao;

import com.edu.base.BaseDao;
import com.edu.pojo.Resume;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* 一个符合springDataJpa要求的Dao层接口是需要继承JpaRepository和JpaSpecificationExecutor
*
* JpaRepository<T,ID>T操作的实体类类型  ID 主键类型
*   封装了基本的CRUD操作
* JpaSpecificationExecutor<T>
    封装了复杂查询排序 分页等
* */
public interface ResumeDao extends BaseDao<Resume,Long> {
      @Query("from Resume where id=?1")
      Resume findByJpql(Long id);

    /**
     * 使用原生sql 需要将nativeQuery=true
     * @param id
     * @return
     */
      @Query(value="select * from tb_resume where id=?1",nativeQuery=true)
      Resume findBySql(Long id);

    /**
     * 方法名规则查询
     * 按照name模糊查询(like)
     * 方法名以findBy开头
     *              -属性名(首字母大写)
     *                -查询方式(模糊,等价) 如果不加查询方式默认是等价
     */
    List<Resume> findByNameLikeAndAddress(String name,String address);
}
