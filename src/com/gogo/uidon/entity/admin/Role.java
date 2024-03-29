package com.gogo.uidon.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 角色实体类类
 * @author Administrator
 *
 */
@Component
public class Role {

	private Long id;
	
	private String name;
	
	private String remark;//备注

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
