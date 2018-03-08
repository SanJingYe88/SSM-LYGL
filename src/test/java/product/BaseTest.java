package product;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	@Test
	public void testDruid(){
		
		ClassPathXmlApplicationContext  ctx=
		new ClassPathXmlApplicationContext("spring-pool.xml");
		
		DataSource dataSource=
		(DataSource)ctx.getBean("dataSource");
	    System.out.println("dataSource="+dataSource);
		
	    //验证dataSource是否不等于null
	    Assert.assertNotEquals(null, 
	    		dataSource);
	    ctx.close();
	    
	}
	@Test
	public void testSessionFty(){
		ClassPathXmlApplicationContext ctx=
		new ClassPathXmlApplicationContext(
				"spring-pool.xml",
				"spring-mybatis.xml");
		Object bean=
		ctx.getBean("sqlSessionFactory");
		System.out.println(bean);
		Assert.assertNotEquals(null, bean);
		ctx.close();
	}
}
