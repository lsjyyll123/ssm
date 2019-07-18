package com.gogo.uidon.interceptor.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gogo.uidon.entity.admin.Menu;
import com.gogo.uidon.util.MenuUtil;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest res, HttpServletResponse rps, Object o) throws Exception {
		
		String resurls=res.getRequestURI();	
		System.out.println("进入拦截起的：url："+resurls);
		Object admin=res.getSession().getAttribute("admin");
		if(admin == null){
			
			//重定向到登陆页面
			//判断是否是ajax
			String header=rps.getHeader("X-Requested-With");
			if("XMLHttpRequest".equals(header)){
				Map<String, String> ret=new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登陆会话超时或者还未登陆，请重新登录！");
				rps.getWriter().write(JSONObject.fromObject(ret).toString());
				System.out.println("进入拦截起的  被拦截 ajxa  XMLHttpRequest：url：");
				return false;
			}
			System.out.println("进入拦截起的  被拦截：url："+resurls);
			rps.sendRedirect(res.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		
		/**
		 * 
		 */
		
		String mid = res.getParameter("_mid");
		if(!StringUtils.isEmpty(mid)){
			List<Menu> allThirdMenu = MenuUtil.getAllThirdMenu((List<Menu>)res.getSession().getAttribute("userMenus"), Long.valueOf(mid));
			res.setAttribute("thirdMenuList", allThirdMenu);
		}
		
		return true;
	}

}
