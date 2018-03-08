package product.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.entity.Team;

public class TestTeamDao {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx= new ClassPathXmlApplicationContext(
				"spring-pool.xml",
				"spring-mybatis.xml");
	}
	@Test
	public void testInsertObject(){
		TeamDao teamDao=
		ctx.getBean("teamDao",TeamDao.class);
		Team t=new Team();
		t.setName("环球游50日团");
		t.setProjectId(11);
		t.setValid(1);
		t.setNote("环球游50日团....");
		int rows=
		teamDao.insertObject(t);
		System.out.println(rows);
		//判定rows这个实际的值是否不等于不期望出现的值-1
		//Assert.assertNotEquals(-1, rows);
		//测试表达式的值是否为true
		//Assert.assertTrue(rows==1);
		//判定rows的值是否是我们期望出现的值1
		Assert.assertEquals(1, rows);
	}
	public void destory(){
		ctx.close();
	}
}
