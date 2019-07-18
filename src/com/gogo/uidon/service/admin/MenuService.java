package com.gogo.uidon.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gogo.uidon.entity.admin.Menu;

/**
 * 菜单接口
 * @author Administrator
 *
 */
@Service
public interface MenuService {
	public int add(Menu menu);
	public List<Menu> findList(Map<String, Object> queryMap);
	public List<Menu> findTopList();
	public int getTotal(Map<String, Object> queryMap);
	public int edit(Menu menu);
	public int delete(Long id);
	public List<Menu> findChildernList(Long parentId);
	public List<Menu> findListByIds(String ids);
}
