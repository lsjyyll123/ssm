package com.gogo.uidon.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogo.uidon.entity.admin.Authority;
import com.gogo.uidon.entity.admin.Menu;
import com.gogo.uidon.entity.admin.Role;
import com.gogo.uidon.page.admin.Page;
import com.gogo.uidon.service.admin.AuthorittyService;
import com.gogo.uidon.service.admin.MenuService;
import com.gogo.uidon.service.admin.RoleService;

/**
 * 角色管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/role")
@Controller
public class RoleController {
	@Autowired
	private  RoleService roleService;
	@Autowired
	private  AuthorittyService athService;
	@Autowired
	private  MenuService  menuService;
	
	/**
	 * 角色管理页面
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView m) {
		
		m.setViewName("/role/list");
		return m;
	}
	/**
	 * 角色列表数据
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRoleList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		List<Role> findList=roleService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", roleService.getTotal(queryMap));
		return ret;
		
		 
	}
	/**
	 * 角色添加
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Role role){
		Map<String, Object> ret = new HashMap<String, Object>();
		 if(role == null) {
			 ret.put("type", "error");
			 ret.put("msg", "请填写角色信息！");
		 }
		 if(StringUtils.isEmpty(role.getName())) {
			 ret.put("type", "error");
			 ret.put("msg", "请填写角色名称！");
		 }
		 
		 if(roleService.add(role)<=0) {
			 ret.put("type", "error");
			 ret.put("msg", "添加失败！");
		 }
		 ret.put("type", "success");
		 ret.put("msg", "添加成功！");
		return ret;
		
		
	}
	/**
	 * 角色修改
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(Role role){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(role == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写角色信息！");
		}
		if(StringUtils.isEmpty(role.getName())) {
			ret.put("type", "error");
			ret.put("msg", "请填写角色名称！");
		}
		
		if(roleService.edit(role)<=0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败！");
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
		
		
	}
	/**
	 * 角色删除
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(id == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的角色！");
		}
		 try {
			 if(roleService.delete(id)<=0) {
					ret.put("type", "error");
					ret.put("msg", "删除失败！");
				}
		} catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "该角色下存在权限或者用户信息，不能删除！");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
		
	}
	
	/**
	 * 查询所有的菜单
	 */
	@RequestMapping(value="/get_all_menu",method=RequestMethod.POST)
	@ResponseBody
	public List<Menu> getAllMenu(){
		Map<String, Object> ret=new HashMap<String, Object>();
		ret.put("offset", 0);
		ret.put("pagesize", 9999999);
		return  menuService.findList(ret);
		
	}
	/**
	 * 权限编辑
	 */
	@RequestMapping(value="/add_authority",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addAuthority(@RequestParam(name="ids",required = true) String ids,
			@RequestParam(name="roleId",required = true)Long roleId) {
		Map<String, String> ret=new HashMap<String, String>();
		if(ids == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的角色！");
			return ret;
		}
		if(ids.contains(",")) {
			ids=ids.substring(0,ids.length()-1);
		}
		String[] idarr=ids.split(",");
		if(idarr.length>0) {
			athService.deleteByRoleId(roleId);
		}
		for(String id:idarr) {
			Authority ath= new Authority();
			ath.setMenuId(Long.valueOf(id));
			ath.setRoleId(roleId);
			athService.add(ath);
		}
		ret.put("type", "success");
		ret.put("msg", "权限编辑成功！");
		return ret;
	}
	/**
	 * 获取某个的所有的权限
	 */
	@RequestMapping(value="/get_role_authority",method=RequestMethod.POST)
	@ResponseBody
	public List<Authority> getRoleAuthority(@RequestParam(name="roleId",required = true) Long roleId){
		Authority ath=new Authority();
		 
		return athService.findByRoleId(roleId);
	}
}
