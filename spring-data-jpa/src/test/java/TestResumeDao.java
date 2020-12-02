import com.edu.dao.ResumeDao;
import com.edu.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestResumeDao {
    @Autowired
    private ResumeDao resumeDao;

    /**
     * dao层接口调用分为两部分
     * 1 基础的增删改查
     * 2 专门针对查询的详细分析使用
     */
    @Test
    public void testFindById() {
        // 早期版本findOne(id)
        /*
         * select resume0_.id as id1_0_0_,
         * resume0_.address as address2_0_0_,
         * resume0_.name as name3_0_0_,
         * resume0_.phone as phone4_0_0_
         * from tb_resume resume0_ where resume0_.id=?
         * */
        Optional<Resume> optional = resumeDao.findById(1l);
        Resume resume = optional.get();
        System.out.println(resume);
    }

    @Test
    public void testFindOne() {
        Resume resume = new Resume();
        resume.setId(1l);
        resume.setName("张三");
        Example<Resume> re = Example.of(resume);
        Optional<Resume> resume1 = resumeDao.findOne(re);
        System.out.println(resume1.get());
    }

    @Test
    public void testSave() {
        Resume resume = new Resume();
        resume.setId(4l);
        resume.setName("三四");
        resume.setAddress("深圳");
        resume.setPhone("123456789101");
        //新增和修改 根据传入对象的有无主键 来判别。有主键就是更新
        Resume save = resumeDao.save(resume);
        System.out.println(save);
    }

    @Test
    public void testDelete() {
        Resume resume = new Resume();
        resume.setId(4l);
        resume.setName("三四");
        resume.setAddress("深圳");
        resume.setPhone("123456789101");
        resumeDao.deleteById(4l);
        //  System.out.println(save);
    }

    @Test
    public void testFindAll() {
        List<Resume> list = resumeDao.findAll();
        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }

    }

    @Test
    public void testSort() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<Resume> list = resumeDao.findAll(sort);
        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }

    }

    @Test
    public void testPageable() {
        /**
         * 第一个参数 :是当前查询的页数 从0开始
         * 第二个参数: 是每页查询的数量
         */
       // Pageable page = new PageRequest(0,2);过时了
        Pageable pageable = PageRequest.of(0, 2);
        Page<Resume> all = resumeDao.findAll(pageable);
        System.out.println("all = " + all);


    }

    /**
     * ##############################针对查询的使用进行分析#####################################
     * 方式一 调用接口中继承的方法 findOne   findById
     * 方式二  可以引用jpql(jpa语言)进行查询
     * 方式三  可以引用原生的sql
     * 方式四  可以在接口中定义方法 并且不必引用sql 和jpql  这种方法就做方法命名规则
     * 也就是说 接口定义的方法名 是按照一定的规则形成的 那么框架就能明白哦们的意图
     * 方式五  动态查询
     *              service层传入dao层条件不确定,把service拿到的条件封装成一个对象,传递给dao层,
     *              这个对象就是specification(条件的封装)
     *              Optional<T> findOne(@Nullable Specification<T> spec);
     *              List<T> findAll(@Nullable Specification<T> spec);
     *              Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
     *              List<T> findAll(@Nullable Specification<T> spec, Sort sort);
     *              long count(@Nullable Specification<T> spec);
     *              interface  Specification<T>
     *                  Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
     *                  用来封装查询条件的
     *                  Root 根属性(查询所需要的的实体类任何属性)
     *                  CriteriaQuery 自定义查询方式 一般用不上
     *                  CriteriaBuilder 查询构造器 封装了很多查询条件(like = 等)
     */
    @Test
    public void testFindByJpql() {
        Resume byJpql = resumeDao.findByJpql(1l);

        System.out.println("resume = " + byJpql);

    }
    @Test
    public void testFindBySql() {
        Resume bySql = resumeDao.findBySql(1l);
        System.out.println("resume = " + bySql);

    }
    @Test
    public void testFindByNameLike(){
        List<Resume> byNameLike = resumeDao.findByNameLikeAndAddress("张%","北京");
        for (int i = 0; i < byNameLike.size(); i++) {
            Resume resume =  byNameLike.get(i);
            System.out.println(resume);
        }
    }

    @Test
    public void testSpecification(){
        //查询条件的封装specification
        /**
         * 动态条件封装
         * 匿名内部类
         * toPredicate 动态组装查询条件
         *  借助于两个参数完成条件拼装
         *  Root :获取需要查询的对象的属性
         *  CriteriaBuilder: 构建查询条件,内部封装很多查询条件(模糊查询,精准查询等)
         *  需求 根据name(指定张三) 查询简历
         */
        Specification<Resume> specification = new Specification<Resume>() {
            @Override
            public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
               //获取name属性
                Path<Object> name = root.get("name");
                //使用CriteriaBuilder针对name属性构建条件(精准查询)
                Predicate predicate = criteriaBuilder.equal(name, "张三");
                return predicate;
            }
        };
        Optional<Resume> optional = resumeDao.findOne(specification);
        Resume resume = optional.get();
        System.out.println("resume = " + resume);

    }

    @Test
    public void testSpecificationMultiCon(){
        //查询条件的封装specification

        Specification<Resume> specification = new Specification<Resume>() {
            @Override
            public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
               //获取name属性
                Path<Object> name = root.get("name");
                Path<Object> address = root.get("address");
                //使用CriteriaBuilder针对name属性构建条件(精准查询)
                //条件1
                Predicate predicate1 = criteriaBuilder.equal(name, "张三");
                //条件2:address 以北开头
                Predicate predicate2 = criteriaBuilder.like(address.as(String.class), "北%");
                //组合两个条件
                Predicate and = criteriaBuilder.and(predicate1, predicate2);

                return and;
            }
        };
        Optional<Resume> optional = resumeDao.findOne(specification);
        Resume resume = optional.get();
        System.out.println("resume = " + resume);

    }

}
