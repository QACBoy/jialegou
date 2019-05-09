import com.hilkr.comments.JialegouCommentsApplication;
import com.hilkr.comments.dao.SpitDao;
import com.jialegou.comments.pojo.Spit;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JialegouCommentsApplication.class)
public class MongoDBTests {

    @Autowired
    private SpitDao spitDAO;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        Spit spit = new Spit();
        spit.set_id("1");
        spit.setContent("test");
        spit.setNickname("测试");
        spit.setUserid("123456");
        spit.setVisits(1234);
        spitDAO.save(spit);
    }

    @Test
    public void findTest() {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("_id").is("1"), Criteria.where("visits").is(1234));
        List<Spit> spits = this.mongoTemplate.find(new Query(criteria), Spit.class);
        System.out.println("数量：" + spits.size());
        for (Spit spit : spits) {
            System.out.println(spit);
        }
    }

    @Test
    public void queryTest() {
        //1.创建连接
        // MongoClient client = new MongoClient("localhost", 27017);
        //2.打开数据库
        // MongoDatabase database = client.getDatabase("spitdb");
        //3.获取集合
        MongoCollection<Document> spit = mongoTemplate.getCollection("spit");
        //4.查询记录获取文档集合
        FindIterable<Document> documents = spit.find();
        //5.输出
        for (Document document : documents) {
            System.out.println("用户ID：" + document.getString("userid"));
            System.out.println("用户昵称：" + document.getString("nickname"));
            System.out.println("内容：" + document.getString("content"));
            System.out.println("浏览量：" + document.getInteger("visits"));
            System.out.println("---------------------------------------------");
        }
    }
}
