import com.hilkr.order.JialegouOrderApplication;
import com.hilkr.order.service.OrderStatusService;
import com.hilkr.order.vo.OrderStatusMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JialegouOrderApplication.class)
public class RabbitMQTest {

    @Autowired
    private OrderStatusService orderStatusService;

    @Test
    public void sendMessage() {
        OrderStatusMessage orderStatusMessage = new OrderStatusMessage();
        orderStatusMessage.setOrderId(123L);
        orderStatusMessage.setType(1);
        orderStatusMessage.setUserId(111L);
        orderStatusService.sendMessage(orderStatusMessage);
    }
}
