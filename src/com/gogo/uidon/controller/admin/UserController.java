package com.gogo.uidon.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gogo.uidon.entity.admin.User;
import com.gogo.uidon.page.admin.Page;
import com.gogo.uidon.service.admin.RoleService;
import com.gogo.uidon.service.admin.UserService;

/**
 * 用户管理
 * @author Administrator
 *
 */
@RequestMapping("/admin/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 用户管理页面
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView m) {
		
		Map<String, Object> queryMap=new HashMap<String, Object>();
		m.addObject("roleList",roleService.findList(queryMap));
		m.setViewName("user/list");
		return m;
	}
	
	/**
	 * 获取用户列表
	 * @param page
	 * @param username
	 * @param UserId
	 * @param sex
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
		 @RequestParam(name="username",required=false,defaultValue="") String username,
		 @RequestParam(name="roleId",required=false,defaultValue="") Long roleId,
		 @RequestParam(name="sex",required=false,defaultValue="") Integer sex
		 ) {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", username);
		queryMap.put("roleId", roleId);
		queryMap.put("sex", sex);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", userService.findList(queryMap));
		ret.put("total", userService.getTotal(queryMap));
		return ret;
		
		
	}
	/**
	 *用户添加
	 * @param User
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(User user){
		Map<String, Object> ret = new HashMap<String, Object>();
			if(user == null){
				ret.put("type", "error");
				ret.put("msg", "请填写用户信息!");
				return ret;
			}
			if(StringUtils.isEmpty(user.getUsername())){
				ret.put("type", "error");
				ret.put("msg", "请填写用户名！");
				return ret;
			}
			if(StringUtils.isEmpty(user.getPassword())){
				ret.put("type", "error");
				ret.put("msg", "请填写密码！");
				return ret;
			}
			if(user.getRoleId() == null){
				ret.put("type", "error");
				ret.put("msg", "请选择角色！");
				return ret;
			}
			if(user.getAge() == null){
				ret.put("type", "error");
				ret.put("msg", "请填写年龄！");
				return ret;
			}
			if(user.getSex() == null){
				ret.put("type", "error");
				ret.put("msg", "请填写性别！");
				return ret;
			}
			if(isExist(user.getUsername(), 0l)){
				ret.put("type", "error");
				ret.put("msg", "该用户名已经存在！");
				return ret;
			}
			if(userService.add(user) <= 0){
				ret.put("type", "error");
				ret.put("msg", "添加失败！");
				return ret;
			}
			ret.put("type", "success");
			ret.put("msg", "添加成功！");
			return ret;
	}
	/**
	 * 用户修改
	 * @param User
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(User user){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息!");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		 
		if(user.getRoleId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择角色！");
			return ret;
		}
		if(user.getAge() == null){
			ret.put("type", "error");
			ret.put("msg", "请填写年龄！");
			return ret;
		}
		if(user.getSex() == null){
			ret.put("type", "error");
			ret.put("msg", "请填写性别！");
			return ret;
		}
		if(isExist(user.getUsername(), user.getId())){
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在！");
			return ret;
		}
		if(userService.edit(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "修改失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功！");
		return ret;
		
		
	}
	/**
	 * 角色删除
	 * @param User
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(String ids){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StringUtils.isEmpty(ids)){
			ret.put("type", "error");
			ret.put("msg", "请选择用户！");
			return ret;
		}
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
	    if(userService.delete(ids)<=0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败！");
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
		
	}

	/**
	 * 上传头像
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/upload_photo",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadPhoto(MultipartFile photo, HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		 File targetFile=null;
		if(photo == null){
			ret.put("type", "error");
			ret.put("msg", "没有选择图片！");
			return ret;
		}
		if(photo.getSize() > 1024*1024*10){
			ret.put("type", "error");
			ret.put("msg", "图片尺寸太大，选择小于10M的图片");
			return ret;
		}
		//获取图片后缀
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
		if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
			return ret;
		}
		
		String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
		File savePathFile = new File(savePath);
		 //如果文件夹不存在则创建    
        if(!savePathFile.exists() && !savePathFile.isDirectory()){       
        	savePathFile.mkdir();  
        }
		String filename = new Date().getTime()+"."+suffix;
		try {
			//将图片存入文件夹
            targetFile = new File(savePathFile, filename);
                //将上传的文件写到服务器上指定的文件。
//			 photo.transferTo(new File(savePath+filename));
			 photo.transferTo(targetFile);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "上传失败！");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "上传成功！");
		ret.put("filepath",savePath+ filename );
		return ret;
	}
	/**
	 * 判断是否存在这个用户了
	 * @param username
	 * @param id
	 * @return
	 */
	private boolean isExist(String username,Long id){
		User user = userService.findByUsername(username);
		if(user == null)return false;
		if(user.getId().longValue() == id.longValue())return false;
		return true;
	}
}
