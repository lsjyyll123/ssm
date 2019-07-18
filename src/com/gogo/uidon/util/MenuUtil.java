package com.gogo.uidon.util;

import java.util.ArrayList;
import java.util.List;

import com.gogo.uidon.entity.admin.Menu;

/**
 * 菜单工具
 * @author lsj
 *
 */
public class MenuUtil {
	/**
	 * 获取顶级菜单
	 * @param menuList
	 * @return
	 */
	public static List<Menu> getAllTopMenu(List<Menu> menuList){
		List<Menu> ret = new ArrayList<Menu>();
		for(Menu menu:menuList){
			System.err.println("menu.getParentId()-:"+menu.getParentId());
			if(menu.getParentId() == 0){
				ret.add(menu);
			}
		}
		return ret;
	}
	
	/**
	 * 获取二级菜单目录
	 * @param menuList
	 * @return
	 */
	public static List<Menu> getAllSecondMenu(List<Menu> menuList){
		List<Menu> ret = new ArrayList<Menu>();
		List<Menu> allTopMenu = getAllTopMenu(menuList);
		for(Menu menu:menuList){
			for(Menu topMenu:allTopMenu){
				if(menu.getParentId() == topMenu.getId()){
					ret.add(menu);
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 *  获取某个二级菜单下的按钮url
	 * @param menuList
	 * @param url
	 * @return
	 */
	public static List<Menu> getAllThirdMenu(List<Menu> menuList, Long secondMenuId){
		List<Menu> ret = new ArrayList<Menu>();
		for(Menu menu:menuList){
			if(menu.getParentId() == secondMenuId){
				ret.add(menu);
			}
		}
		return ret;
	}
}
