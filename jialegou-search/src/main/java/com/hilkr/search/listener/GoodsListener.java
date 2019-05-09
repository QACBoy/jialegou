package com.hilkr.search.listener;

import com.hilkr.search.service.ISearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Component
public class GoodsListener {


    @Autowired
    private ISearchService searchService;

    /**
     * 处理insert和update的消息
     * @param id
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "jialegou.create.index.queue",durable = "true"), //队列持久化
            exchange = @Exchange(
                    value = "jialegou.item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"item.insert","item.update"}
    ))
    public void listenCreate(Long id) throws Exception{
        if (id == null){
            return;
        }
        //创建或更新索引
        this.searchService.createIndex(id);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "jialegou.delete.index.queue",durable = "true"), //队列持久化
            exchange = @Exchange(
                    value = "jialegou.item.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"item.delete"}
    ))
    public void listenDelete(Long id){
        if (id == null){
            return;
        }

        //删除索引
        this.searchService.deleteIndex(id);
    }
}
