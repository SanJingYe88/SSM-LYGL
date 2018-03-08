package product.service;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.service.TeamService;

public class TestTeamService {

	private ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext(
		"spring-mvc.xml",
		"spring-pool.xml",
		"spring-mybatis.xml");
	}
	@Test
	public void testFindObjects(){
		//获得service对象
		TeamService teamService=
		ctx.getBean("teamServiceImpl",
				TeamService.class);
		//执行查询
		Map<String,Object> map=
		teamService.findObjects(
				null,//valid
				null,//projectId 
				1);
		System.out.println(map);
		Assert.assertEquals(2, map.size());
		/*List<Map<String,Object>> list=
			(List<Map<String,Object>>)
			map.get("list");
		PageObject pageObject=
		(PageObject)map.get("pageObject");
		Assert.assertEquals(1, pageObject.getPageCount());
		Assert.assertEquals(2,list.size());*/
	}
	@After
	public void destory(){
		ctx.close();
	}
}
