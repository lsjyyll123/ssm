package com.gogo.uidon.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gogo.uidon.entity.admin.Authority;

/**
 * 权限dao
 * @author Administrator
 *
 */
@Repository
public interface AuthorityDao {
	public int add(Authority ath) ;
	public int deleteByRoleId(Long roleId) ;
	public List<Authority> findByRoleId(Long roleId) ;
}
