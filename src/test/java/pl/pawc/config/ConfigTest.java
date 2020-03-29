package pl.pawc.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class ConfigTest extends TestCase {
	
	@Configuration
	@EnableTransactionManagement
	static class ContextConfiguration {
		
		@Bean
		public ViewResolver viewResolver() {
			
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			
			viewResolver.setPrefix("/WEB-INF/jsp/");
			viewResolver.setSuffix(".jsp");
			
			return viewResolver;
			
		}
		
		@Bean
		public ComboPooledDataSource dataSource() throws PropertyVetoException {
			ComboPooledDataSource dataSource = new  ComboPooledDataSource();
			dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=testdb;integratedSecurity=False;");
			dataSource.setUser("testuser");
			dataSource.setPassword("testpassword");
			
			dataSource.setMinPoolSize(5);
			dataSource.setMaxPoolSize(20);
			dataSource.setMaxIdleTime(30000);
			
			return dataSource;
		}
		
		@Bean
		public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			
			sessionFactory.setDataSource(dataSource());
			sessionFactory.setPackagesToScan("pl.pawc.entity");
			
			Properties properties = new Properties();
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");
			properties.setProperty("hibernate.show_sql", "true");
			
			sessionFactory.setHibernateProperties(properties);
			
			return sessionFactory;
		}
		
		@Bean
		public HibernateTransactionManager transactionManager() throws PropertyVetoException {
			HibernateTransactionManager manager = new HibernateTransactionManager();
			
			manager.setSessionFactory(sessionFactory().getObject());
			
			return manager;
		}
		
	}
	
	@Test
	public void testA() {
		System.out.println("ok");
	}

}