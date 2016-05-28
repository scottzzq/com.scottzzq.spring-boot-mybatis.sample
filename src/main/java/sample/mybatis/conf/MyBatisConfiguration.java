package sample.mybatis.conf;

import sample.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class MyBatisConfiguration {
	@Autowired
	MybatisDataSourceProperties properties;
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Autowired
	SqlSessionTemplate sessionTemplate;
    @Autowired
    UserMapper userMapper;
    
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	    
    @Bean  
    public UserMapper userMapper() {    
    	return sessionTemplate.getMapper(UserMapper.class); 
    }
    
	@Bean
	public SqlSessionFactory sqlSessionFactory(){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.properties.getClassLoader())
				.driverClassName(this.properties.getDriverClassName())
				.url(this.properties.getUrl())
				.username(this.properties.getUsername())
				.password(this.properties.getPassword());
		bean.setDataSource(factory.build());
		ClassPathResource classPathResource = new ClassPathResource("UserMapper.xml");
		Resource[] resources = {classPathResource};
		bean.setMapperLocations(resources);
		SqlSessionFactory sqlSessionFactory = null;  
         try {  
                sqlSessionFactory = bean.getObject();  
         }catch (Exception e) {  
                e.printStackTrace();  
                System.exit(0);  
         }
         return sqlSessionFactory;
	}
}
