package com.edu.dao;

import com.edu.pojo.Resume;

/*
* 一个符合springDataJpa要求的Dao层接口是需要继承JpaRepository和JpaSpecificationExecutor
*
* JpaRepository<T,ID>T操作的实体类类型  ID 主键类型
*   封装了基本的CRUD操作
* JpaSpecificationExecutor<T>
    封装了复杂查询排序 分页等
* */
public interface ResumeDao extends BaseDao<Resume,Long> {

}
