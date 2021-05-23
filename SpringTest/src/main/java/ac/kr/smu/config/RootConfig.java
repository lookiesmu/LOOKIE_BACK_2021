package ac.kr.smu.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
@MapperScan("ac.kr.smu.mapper")
@ComponentScan(basePackages = {"ac.kr.smu.service", "ac.kr.smu.provider", "ac.kr.smu.filter"})
public class RootConfig {
    @Autowired
    private ApplicationContext applicationContext;

//    @Bean
//    public ComboPooledDataSource comboPooledDataSource() {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try {
//            dataSource.setDriverClass("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
//            dataSource.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/spring?allowMultiQueries=true");
//            dataSource.setUser("myunbongs");
//            dataSource.setPassword("1030");
//            dataSource.setCheckoutTimeout(3000);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataSource;
//    }

    @Bean
    public ComboPooledDataSource comboPooledDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try{
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spring?allowMultiQueries=true");
            dataSource.setUser("myunbongs");
            dataSource.setPassword("1030");
            dataSource.setCheckoutTimeout(1000);
        }catch (Exception e){e.printStackTrace();}

        return dataSource;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSources){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        try {
            sqlSessionFactoryBean.setDataSource(dataSources);
            sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
            sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));
        }catch (IOException e){ e.printStackTrace();}

        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactoryBean){
        return new SqlSessionTemplate(sqlSessionFactoryBean);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


}
