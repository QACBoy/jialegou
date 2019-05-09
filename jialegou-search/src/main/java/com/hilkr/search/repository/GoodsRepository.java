package com.hilkr.search.repository;

import com.hilkr.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
