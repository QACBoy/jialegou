package com.hilkr.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author sam
 * @create 2019-03-20
 */
@EnableTransactionManagement
@Configuration
@Slf4j
// 无法被引用的包扫描到
public class MybatisPlusConfig implements TransactionManagementConfigurer {


    @Autowired
    private DataSource dataSource;

    private final String mapperLocations = "classpath*:com/hilkr/dal/mapper*/*.xml";

    // private final int dst = 30;

    // 提供SqlSeesion
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            // 手写配置
            // 配置类型别名
            sessionFactoryBean.setTypeAliasesPackage("com.hilkr.dal.model");

            // 配置mapper的扫描，找到所有的mapper.xml映射文件
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:com/hilkr/dal/mapper/*.xml");
            sessionFactoryBean.setMapperLocations(resources);

            // 加载全局的配置文件
            // sessionFactoryBean.setConfigLocation(
            //         new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));

            //添加插件
            // sessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            log.warn("mybatis resolver mapper*xml is error");
            return null;
        } catch (Exception e) {
            log.warn("mybatis sqlSessionFactoryBean create error");
            return null;
        }
    }


    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    // @Bean
    // public PageHelper pageHelper(){
    //     log.info("MyBatis分页插件PageHelper");
    //     //分页插件
    //     PageHelper pageHelper = new PageHelper();
    //     Properties properties = new Properties();
    //     properties.setProperty("offsetAsPageNum", "true");
    //     properties.setProperty("rowBoundsWithCount", "true");
    //     properties.setProperty("reasonable", "true");
    //     properties.setProperty("supportMethodsArguments", "true");
    //     properties.setProperty("returnPageInfo", "check");
    //     properties.setProperty("params", "count=countSql");
    //     pageHelper.setProperties(properties);
    //     return pageHelper;
    // }
    // /**
    //  * 分页插件
    //  */
    // @Bean
    // public PaginationInterceptor paginationInterceptor() {
    //     return new PaginationInterceptor();
    // }

    // @Bean
    // public SqlSessionFactory sqlSessionFactory() throws Exception{
    //     SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    //     sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
    //     log.info("sqlSessionFactory:--->mybatis.mapperLocation:" + mapperLocations );
    //
    //     sqlSessionFactoryBean.setDataSource(dataSource);
    //     //configuration
    //     org.apache.ibatis.session.Configuration cfg = new org.apache.ibatis.session.Configuration();
    //     //设置相关参数，我这里就只用了一个
    //     cfg.setDefaultStatementTimeout(dst);
    //     log.info("sqlSessionFactoryBean:-->" + sqlSessionFactoryBean.getObject());
    //     log.info("default-statement-timeout:" + dst);
    //     sqlSessionFactoryBean.setConfiguration(cfg);
    //     return sqlSessionFactoryBean.getObject();
    // }
}
