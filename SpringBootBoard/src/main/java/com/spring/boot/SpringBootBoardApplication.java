package com.spring.boot;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/*
src/main/java : 패키지와 클래스 파일
src/main/resource 
-static : css/js 파일
-templates : html파일(표준)
-application.properties : 환경설정파일
-pom.xml : Maven 라이브러리 설정파일
 */


@SpringBootApplication
public class SpringBootBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBoardApplication.class, args);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		Resource[] res = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mybatis/mapper/*.xml");

		sessionFactory.setMapperLocations(res);

		return sessionFactory.getObject();

	}


}

