package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;


//当某个业务类上使用了 @Transaction 注解时,Spring默认就会通过 AOP为此类创建代理对象,然后通多代理对象为 业务方法织入事务处理功能.
@Transactional
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	@Resource
	private ProductTypeDao productTypeDao;

	//查询产品分类列表信息
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Map<String, Object>> findGridTreeObjects() {
		return productTypeDao.findObjects();
	}

	//查询分类节点信息,在client端以zTree形式展示
	@Override
	public List<Map<String, Object>> findZtreeNodes() {
		return productTypeDao.findZtreeNodes();
	}

	//删除
	@Override
	public void deleteObject(Integer id) {
		if (id == null || id <= 0){
			throw new ServiceException("id的值无效,id=" + id);
		}
		// 2.判定此分类下有没有子元素
		int count = productTypeDao.hasChilds(id);
		if (count > 0){
			throw new ServiceException("此分类下有子元素,不能删除");
		}
		// 3.执行删除操作
		int rows = productTypeDao.deleteObject(id);
		// 4.根据结果判定删除操作是否OK
		if (rows == -1){
			throw new ServiceException("删除失败");
		}
	}

	/// 产品类型信息的保存
	@Override
	public void saveObject(ProductType entity) {
		if (entity == null){
			throw new ServiceException("保存的对象不能为空");
		}
		int rows = productTypeDao.insertObject(entity);
		if (rows == -1){
			throw new ServiceException("保存失败");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Map<String, Object> findMapById(Integer id) {
		if (id == null){
			throw new ServiceException("id 不能为空");
		}
		Map<String, Object> type = productTypeDao.findMapById(id);
		if (type == null){
			throw new ServiceException("没找到对应的对象");
		}
		return type;
	}

	@Override
	public void updateObject(ProductType entity) {
		// 1.判定参数的有效性
		if (entity == null){
			throw new ServiceException("修改对象不能为空");
		}
		if (entity.getId() == null || entity.getId() <= 0){
			throw new ServiceException("id的值无效,id=" + entity.getId());
		}
		this.findMapById(entity.getId());
		int rows = productTypeDao.updateObject(entity);
		if (rows == -1){
			throw new ServiceException("更新失败");
		}
	}
}
