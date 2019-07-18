package com.gogo.uidon.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogo.uidon.dao.admin.MenuDao;
import com.gogo.uidon.entity.admin.Menu;
import com.gogo.uidon.service.admin.MenuService;

/**
 * 实现菜单接口
 * @author Administrator
 *
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao mennuDao;
	@Override
	public List<Menu> findTopList() {
		 
		return mennuDao.findTopList();
	}
	@Override
	public int add(Menu menu){
		
		return mennuDao.add(menu);
	}
	@Override
	public List<Menu> findList(Map<String, Object> queryMap) {
		return mennuDao.findList(queryMap);
	}
	@Override
	public int getTotal(Map<String, Object> queryMap) {
		 
		return mennuDao.getTotal(queryMap);
	}
	@Override
	public int edit(Menu menu) {
		 
		return mennuDao.edit(menu);
	}
	@Override
	public int delete(Long id) {
		 System.out.println("delete:"+id);
		return mennuDao.delete(id);
	}
	@Override
	public List<Menu> findChildernList(Long parentId) {
		 
		return mennuDao.findChildernList(parentId);
	}
	@Override
	public List<Menu> findListByIds(String ids) {
		 
		return mennuDao.findListByIds(ids);
	}
}
