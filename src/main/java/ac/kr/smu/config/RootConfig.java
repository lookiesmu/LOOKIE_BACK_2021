package ac.kr.smu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan("ac.kr.smu.mapper")
@EnableTransactionManagement //트랜잭션 관리를 위한 Annotation
public class RootConfig {
    @Autowired
    private ApplicationContext applicationContext;
    /*
    	커넥션 풀을 이용해 DB에 연결하는 클래스
    */
    @Bean
    public ComboPooledDataSource comboPooledDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try{
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/DB_POST");
            dataSource.setUser("spring_yoon");
            dataSource.setPassword("111111");
            dataSource.setCheckoutTimeout(1000);
        }catch (Exception e){e.printStackTrace();}

        return dataSource;
    }
    /*
     MyBatis 설정을 위한 클래스
    */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSources){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        try{
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
    /*
    	트랜잭션을 위한 클래스
    */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}