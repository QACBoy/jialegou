package com.hilkr.page.service;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface IGoodsHtmlService {

    /**
     * 创建html页面
     * @param spuId
     */
    void createHtml(Long spuId);

    /**
     * 新建线程处理页面静态化，Controller调用
     * @param spuId
     */
    void asyncExecute(Long spuId);

    /**
     * 删除html页面
     * @param id
     */
    void deleteHtml(Long id);
}
