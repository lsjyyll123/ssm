package com.gogo.uidon.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @author Administrator
 *
 */
@Component
public class User {
	private Long id;//用户id
	private String username;//用户名
	private Long roleId;//所属角色id
	private String password;// 密码
	private String photo;//头像照片地址
	private String sex;// 性别  1男 2 女
	private String age;//年龄
	private String address;//家庭住址
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoro() {
		return photo;
	}
	public void setPhoro(String phoro) {
		this.photo = phoro;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
