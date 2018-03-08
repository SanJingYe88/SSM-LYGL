package product.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;

public class TestProductTypeDao {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx= new ClassPathXmlApplicationContext(
				"spring-pool.xml",
				"spring-mybatis.xml");
	}
	@Test
	public void testInsertObject(){
		ProductTypeDao typeDao=
		ctx.getBean("productTypeDao",ProductTypeDao.class);
		ProductType t=new ProductType();
		t.setName("亲子游");
		t.setSort(2);
		t.setParentId(1);
		t.setNote("亲子游...");
		int rows=typeDao.insertObject(t);
		Assert.assertEquals(1, rows);
	}
	public void destory(){
		ctx.close();
	}
}
