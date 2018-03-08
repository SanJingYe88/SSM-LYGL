package product.service;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.service.ProjectService;

public class TestProjectService {
	private ClassPathXmlApplicationContext ctx;
    private ProjectService projectService;
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext(
		"spring-mvc.xml",
		"spring-pool.xml",
		"spring-mybatis.xml");
		projectService=
		ctx.getBean("projectServiceImpl",//@Service
			ProjectService.class);
	}//ctrl+shift+o (快速引入类)
	@Test
	public void testFindObjects(){
		/*List<Project> list=
		projectService.findObjects();
		System.out.println(list);
		//验证list集合是否不等于null
		Assert.assertNotEquals(
				null,//unexpected
				list);//actual
*/	}
	@Test
	public void destory(){
		ctx.close();
		projectService=null;
	}
}
