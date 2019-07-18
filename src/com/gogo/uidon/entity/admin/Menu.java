package com.gogo.uidon.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 菜单实体
 * @author  lsj
 *
 */
@Component
public class Menu {
	private Long id;
	private Long parentId;//父类idid
	private Long _parentId;// id, 匹配easyui的父类id
	private String name;//菜单名称
	private String url;//点击后的地址url
	private String icon;//菜单图标
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Long get_parentId() {
		_parentId = parentId;
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
}
