import com.edu.dao.ResumeDao;
import com.edu.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void testXXX() {
        List<Resume> list = resumeDao.findAll();
        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }

    }

    /**
     * ##############################针对查询的使用进行分析#####################################
     * 方式一 调用接口中继承的方法 findOne   findById
     * 方式二  可以引用jpql(jpa语言)进行查询
     * 方式三  可以引用原生的sql
     * 方式四  可以在接口中定义方法 并且不必引用sql 和jpql  这种方法就做方法命名规则
     * 也就是说 接口定义的方法名 是按照一定的规则形成的 那么框架就能明白哦们的意图
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

}
