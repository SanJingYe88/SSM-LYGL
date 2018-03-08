package cn.tedu.ttms.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.system.entity.SysUser;

public interface SysUserDao extends BaseDao<SysUser> {

	List<SysUser> findPageObjects(@Param("username") String username, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int getRowCount(@Param("username") String username);

	SysUser findObjectById(Integer id);

	int validById(@Param("id") Integer id, @Param("valid") Integer valid);

	SysUser findObjectByName(String username);

	//查询用户得权限
	List<String> findUserPermissions(Integer userId);

	// 查询登陆用户可见的所有菜单
	//SysUserMapper.xml
	List<Map<String, Object>> findUserMenus(Integer userId);

}
