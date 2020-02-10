package com.example.demo.mybadtis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.example.demo.dao"})   // scanning 을 통해서 "com.example.demo.dao" 가져옴
public class MyBatisConfig {
	// sqlSessionFactoryBean의 역할을 수행 하는 메서드
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean ssF = new SqlSessionFactoryBean();
		ssF.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssF.setMapperLocations(resolver.getResources("classpath:com/example/demo/mapper/*.xml"));
		ssF.setTypeAliasesPackage("com.example.demo.vo");  // 패키지로 지정 하면 vo안에 Alias가 먹힌다.
		return ssF.getObject();
	}
	// SqlSessionTemplate
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate ss = new SqlSessionTemplate(sqlSessionFactory);
		return ss;
	}
	
}
