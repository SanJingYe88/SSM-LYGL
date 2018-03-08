package cn.tedu.ttms.common.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysUser;
import cn.tedu.ttms.system.service.SysUserService;

@Controller
public class IndexController {
	@Autowired
	private SysUserService userService;

	//跳转到 /WEB-INF/pages/index.jsp
	@RequestMapping("/indexUI.do")
	public String indexUI() {
		System.out.println("indexUI");
		return "index";
	}

	// 查询登陆用户可见的所有菜单
	@RequestMapping("doFindUserMenus.do")
	@ResponseBody
	public JsonResult doFindUserMenus() {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		List<Map<String, Object>> list = userService.findUserMenus(user.getId());
		//{id=11, parentId=3, name=项目信息, url=project/listUI.do},
		//查询登陆用户可见的所有菜单
		return new JsonResult(list);
	}
}
