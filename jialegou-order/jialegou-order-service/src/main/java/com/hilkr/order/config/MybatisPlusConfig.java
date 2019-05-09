package com.hilkr.order.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Configuration
@MapperScan("com.hilkr.dal.dao*")//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，2者配置1份即可
// @PropertySource("application.yml")
public class MybatisPlusConfig {
    //
    // @Autowired
    // DataSource dataSource;
    /**
     * 自定义配置{@link MybatisSqlSessionFactoryBean}
     * 使用mp-boot-starter 完全可以去掉这些配置，使用yml配置方式, 这里只做示范
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/hilkr/dal/mapper/**/*.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.setConfiguration(configuration);
        PaginationInterceptor pagination = new PaginationInterceptor();
        sqlSessionFactory.setPlugins(new Interceptor[]{
                pagination,
                new PerformanceInterceptor()
        });
        // sqlSessionFactory.setGlobalConfig(globalConfig);
        return sqlSessionFactory.getObject();
    }

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    // @Bean
    // public PerformanceInterceptor performanceInterceptor() {
    //     return new PerformanceInterceptor();
    // }

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//         paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
//         /*
//          * 【测试多租户】 SQL 解析处理拦截器<br>
//          * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
//          */
//         List<ISqlParser> sqlParserList = new ArrayList<>();
//         TenantSqlParser tenantSqlParser = new TenantSqlParser();
//         tenantSqlParser.setTenantHandler(new TenantHandler() {
//             @Override
//             public Expression getTenantId() {
//                 return new LongValue(1L);
//             }
//
//             @Override
//             public String getTenantIdColumn() {
//                 return "tenant_id";
//             }
//
//             @Override
//             public boolean doTableFilter(String tableName) {
//                 // 这里可以判断是否过滤表
//                 /*if ("user".equals(tableName)) {
//                     return true;
//                 }*/
//                 return false;
//             }
//         });
//
//         sqlParserList.add(tenantSqlParser);
//         paginationInterceptor.setSqlParserList(sqlParserList);
// //        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
// //            @Override
// //            public boolean doFilter(MetaObject metaObject) {
// //                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
// //                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
// //                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
// //                    return true;
// //                }
// //                return false;
// //            }
// //        });
        return paginationInterceptor;
    }

    /**
     * 相当于顶部的：
     * {@code @MapperScan("com.baomidou.springboot.mapper*")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    // @Bean
    // public MapperScannerConfigurer mapperScannerConfigurer() {
    //     MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
    //     scannerConfigurer.setBasePackage("com.hilkr.dal.dao*");
    //     return scannerConfigurer;
    // }

    // @Bean
    // public GlobalConfig globalConfig() {
    //     return new GlobalConfig().setDbConfig(new GlobalConfig.DbConfig()
    //             .setIdType(IdType.NONE).setKeyGenerator(new H2KeyGenerator()));
    // }
}