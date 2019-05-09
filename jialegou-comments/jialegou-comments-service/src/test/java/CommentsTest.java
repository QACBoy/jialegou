import com.hilkr.comments.JialegouCommentsApplication;
import com.hilkr.comments.dao.CommentDao;
import com.hilkr.comments.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JialegouCommentsApplication.class)
public class CommentsTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentDao commentDao;

    /**
     * 为spuId为2的商品添加100条评顶级论数据
     */
    @Test
    public void LoadData() {
        for (int i = 0; i < 100; i++) {
            String spuId = "2";
            String content = "手机不错" + i;
            String userId = (35 + i) + "";
            String nickname = "username" + i;
            List<String> images = new ArrayList<>();
            boolean iscomment = i % 2 == 0;
            String parentId = 0 + "";
            boolean isparent = true;
            int type = i % 5;
            //Review review = new Review(spuId, content, userId, nickname, images, iscomment, parentId,isparent,type);

            //commentService.add(review);
        }
    }

    @Test
    public void LoadOneData() {
        String spuId = "2";
        String content = "苹果手机不错";
        String userId = 36 + "";
        String nickname = "username1";
        List<String> images = new ArrayList<>();
        boolean iscomment = true;
        String parentId = "1071767095416725504";
        boolean isparent = false;
        int type = 4;
        //Review review = new Review(spuId, content, userId, nickname, images, iscomment, parentId,isparent,type);

        //commentService.add(review);
    }

    @Test
    public void delete() {
        commentDao.deleteAll();
    }
}
