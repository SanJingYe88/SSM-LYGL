package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.product.entity.ProductType;


// 产品分类的持久层
public interface ProductTypeDao extends BaseDao<ProductType> {
	
	//查询分类信息
	List<Map<String, Object>> findObjects();

	//删除的方法
	int deleteObject(Integer id);

	//判定分类下是否还有子分类
	int hasChilds(Integer id);

	//获得以Ztree形式进行展示的产品分类信息 其中一个节点信息中应该包含: 1)id 2)parentId 3)name
	//每条记录的节点信息都要存储到一个map对象  多个,多个节点信息再存储到list集合.
	List<Map<String, Object>> findZtreeNodes();

	// 根据id获取
	Map<String, Object> findMapById(Integer id);

}
