package com.jialegou.seckill.controller;


import com.hilkr.auth.entity.UserInfo;
import com.hilkr.dal.model.SeckillSku;
import com.hilkr.seckill.vo.SeckillMessage;
import com.jialegou.seckill.access.AccessLimit;
import com.jialegou.seckill.client.GoodsClient;
import com.jialegou.seckill.interceptor.LoginInterceptor;
import com.jialegou.seckill.service.SeckillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@RestController
@RequestMapping
public class SeckillController implements InitializingBean {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "jialegou:seckill:stock";

    private Map<Long, Boolean> localOverMap = new HashMap<>();

    /**
     * 系统初始化，初始化秒杀商品数量
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //1.查询可以秒杀的商品
        BoundHashOperations<String, Object, Object> hashOperations = this.stringRedisTemplate.boundHashOps(KEY_PREFIX);
        if (hashOperations.hasKey(KEY_PREFIX)) {
            hashOperations.entries().forEach((m, n) -> localOverMap.put(Long.parseLong(m.toString()), false));
        }
    }


    /**
     * 秒杀
     *
     * @param path
     * @param seckillSku
     * @return
     */
    @PostMapping("/{path}/seck")
    public ResponseEntity<String> seckillOrder(@PathVariable("path") String path, @RequestBody SeckillSku seckillSku) {

        String result = "排队中";

        UserInfo userInfo = LoginInterceptor.getLoginUser();

        //1.验证路径
        boolean check = this.seckillService.checkSeckillPath(seckillSku.getId(), userInfo.getId(), path);
        if (!check) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //2.内存标记，减少redis访问
        boolean over = localOverMap.get(seckillSku.getSkuId());
        if (over) {
            return ResponseEntity.ok(result);
        }

        //3.读取库存，减一后更新缓存
        BoundHashOperations<String, Object, Object> hashOperations = this.stringRedisTemplate.boundHashOps(KEY_PREFIX);
        Long stock = hashOperations.increment(seckillSku.getSkuId().toString(), -1);

        //4.库存不足直接返回
        if (stock < 0) {
            localOverMap.put(seckillSku.getSkuId(), true);
            return ResponseEntity.ok(result);
        }

        //5.库存充足，请求入队
        //5.1 获取用户信息
        SeckillMessage seckillMessage = new SeckillMessage(userInfo, seckillSku);
        //5.2 发送消息
        this.seckillService.sendMessage(seckillMessage);


        return ResponseEntity.ok(result);
    }

    /**
     * 根据userId查询订单号
     *
     * @param userId
     * @return
     */
    @GetMapping("orderId")
    public ResponseEntity<Long> checkSeckillOrder(Long userId) {
        Long result = this.seckillService.checkSeckillOrder(userId);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result);

    }

    /**
     * 创建秒杀路径
     *
     * @param goodsId
     * @return
     */
    @AccessLimit(seconds = 20, maxCount = 5, needLogin = true)
    @GetMapping("get_path/{goodsId}")
    public ResponseEntity<String> getSeckillPath(@PathVariable("goodsId") Long goodsId) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String str = this.seckillService.createPath(goodsId, userInfo.getId());
        if (StringUtils.isEmpty(str)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(str);
    }

}
