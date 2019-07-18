package com.gogo.uidon.controller.admin;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogo.uidon.entity.admin.Authority;
import com.gogo.uidon.entity.admin.Log;
import com.gogo.uidon.entity.admin.Menu;
import com.gogo.uidon.entity.admin.Role;
import com.gogo.uidon.entity.admin.User;
import com.gogo.uidon.service.admin.AuthorittyService;
import com.gogo.uidon.service.admin.LogService;
import com.gogo.uidon.service.admin.MenuService;
import com.gogo.uidon.service.admin.RoleService;
import com.gogo.uidon.service.admin.UserService;
import com.gogo.uidon.util.CpachaUtil;
import com.gogo.uidon.util.MenuUtil;

/**
 *c
 * @author Administrator
 * */
//http://localhost:91/UserManager/system/index
@Controller
@RequestMapping("/system")
public class SystermController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired
	private AuthorittyService  authorittyService ;

	@Autowired
	private  LogService logService;
	/**
	 * 系统登陆系统界面
	 * @param m
	 * @return
	 */
	 
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView  index(ModelAndView m,HttpServletRequest res) {
		List<Menu> userMenus = (List<Menu>)res.getSession().getAttribute("userMenus");
		System.err.println("-------------："+userMenus.toString());
		m.addObject("topMenuList", MenuUtil.getAllTopMenu(userMenus));
		m.addObject("secondMenuList",MenuUtil.getAllSecondMenu(userMenus));
		 m.setViewName("system/index");
		return m;
		
	}
	/**
	 * 系统首页
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView  welcome(ModelAndView m) {
		m.setViewName("system/welcome");
		m.addObject("name", "今天是周一");
		return m;
		
	}
	/**
	 * 登陆界面
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView m){
		m.setViewName("/system/login");
		return m;
	}
	/**
	 * 
	 * @param u
	 * @param capcha
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> loginActive(User u,HttpServletRequest request){
		String capcha=request.getParameter("cpacha");
		Map<String,String> mp=new HashMap<>();
		if(u==null){
			mp.put("type", "error");
			mp.put("msg", "请填写用户信息!");
			return mp;
		}
		if(StringUtils.isEmpty(capcha)){
			mp.put("type", "error");
			mp.put("msg", "请填写验证码!");
			return mp;
		}
		if(StringUtils.isEmpty(u.getUsername())){
			mp.put("type", "error");
			mp.put("msg", "请填写用户名!");
			return mp;
		}
		if(StringUtils.isEmpty(u.getPassword())){
			mp.put("type", "error");
			mp.put("msg", "请填写密码!");
			return mp;
		}
		Log log=new Log();
		Object loginCapcha=request.getSession().getAttribute("loginCpacha");
		if(!capcha.toUpperCase().equals(loginCapcha.toString().toUpperCase())){
			mp.put("type", "error");
			mp.put("msg", "验证码错误!");
			
			String content="用户"+u.getUsername()+"的用户登录时输入验证码错误!";
			log.setContent(content);
			log.setCreateTime(new Date());
			logService.add(log);
			return mp;
		}
		User findByUsername=userService.findByUsername(u.getUsername());
		
		if(findByUsername==null){
			mp.put("type", "error");
			mp.put("msg", "用户名不存在!");
			String content="用户"+u.getUsername()+"的用户名不存在!";
			log.setContent(content);
			log.setCreateTime(new Date());
			logService.add(log);
			return mp;
		}
		if(!u.getPassword().equals(findByUsername.getPassword())){
			mp.put("type", "error");
			mp.put("msg", "密码不正确!");
			String content="用户"+u.getUsername()+"的密码不正确!";
			log.setContent(content);
			log.setCreateTime(new Date());
			logService.add(log);
			return mp;
		}
		//当用户名和密码正确 
		//查询用户端额角色权限
		String menuIds = "";
		Role role=roleService.find(findByUsername.getRoleId());
		List<Authority> athList= authorittyService.findByRoleId(role.getId());
		for(Authority ath:athList) {
			menuIds+=ath.getMenuId()+",";
		}
		if(!StringUtils.isEmpty(menuIds)){
			menuIds=menuIds.substring(0,menuIds.length()-1);
		}
		
		System.err.println("role-ids----"+role.getId());
		System.err.println("menuss-ids----"+menuIds);
		List<Menu> userMenus=menuService.findListByIds(menuIds);
		System.err.println("menuss-----"+userMenus);
//		把角色信息 菜单信息 用户信息
		request.getSession().setAttribute("admin", findByUsername);
		request.getSession().setAttribute("userMenus", userMenus);
		request.getSession().setAttribute("role", role);
			mp.put("type", "success");
			mp.put("msg", "登陆成功!");
			String content="用户"+u.getUsername()+"的登陆成功!";
			log.setContent(content);
			log.setCreateTime(new Date());
			logService.add(log);
			return mp;
	}
	/**
	   *  退出登录
	 * @param res
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest res) {
		HttpSession hs=res.getSession();
		hs.setAttribute("admin", null);
		hs.setAttribute("userMenus", null);
		hs.setAttribute("role", null);
		
		return "redirect:login";
		
	}
	
	/**
	 * 修改密码也页面
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/edit/password",method=RequestMethod.GET)
	public ModelAndView editPassword(ModelAndView m){
		 m.setViewName("system/editPassword");
		return m;
	}
	/**
	 * 改密码
	 * @param u
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit_password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> editPasswordActiveActive(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String,String> mp=new HashMap<>();
	
		if(StringUtils.isEmpty(newpassword)){
			mp.put("type", "error");
			mp.put("msg", "请填写新的密码!");
			return mp;
		}
		if(StringUtils.isEmpty(oldpassword)){
			mp.put("type", "error");
			mp.put("msg", "请填写原来的密码!");
			return mp;
		}
		User u= (User) request.getSession().getAttribute("admin");
		if(!u.getPassword().equals(oldpassword)) {
			 mp.put("type", "error");
			 mp.put("msg", "原密码错误!");
				return mp;
		}
		u.setPassword(newpassword);
		 if(userService.editPassword(u)<=0) {
			 mp.put("type", "error");
				mp.put("msg", "密码修改失败，联系管理员！!");
				return mp;
		 }
		 
		mp.put("type", "success");
		mp.put("msg", "修改成功!");
		String content="用户"+u.getUsername()+"的修改了密码!";
		Log log= new Log();
		log.setContent(content);
		log.setCreateTime(new Date());
		logService.add(log);
		return mp;
	}
	/**
	 * 验证码获取
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param res
	 * @param rsp
	 */
	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
	public void generateCpacha(
		@RequestParam(name="vl",required=false,defaultValue="4") Integer vcodeLen,
		@RequestParam(name="w",required=false,defaultValue="4") Integer width,
		@RequestParam(name="h",required=false,defaultValue="4") Integer height,
		@RequestParam(name="type",required=true,defaultValue="loginCpacha") String cpachaType,
		HttpServletRequest res,HttpServletResponse rsp
		){
		
		CpachaUtil cu= new CpachaUtil(vcodeLen,width,height);
		String code=cu.generatorVCode();
		res.getSession().setAttribute(cpachaType, code);
		BufferedImage bfi=cu.generatorRotateVCodeImage(code, true);
		try {
			ImageIO.write(bfi, "gif", rsp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
