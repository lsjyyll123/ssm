package com.gogo.uidon.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gogo.uidon.entity.admin.Authority;

/**
 * 权限接口
 * @author Administrator
 *
 */
@Service
public interface AuthorittyService {
	public int add(Authority ath) ;
	public int deleteByRoleId(Long roleId) ;
	public List<Authority> findByRoleId(Long roleId) ;
	
}
