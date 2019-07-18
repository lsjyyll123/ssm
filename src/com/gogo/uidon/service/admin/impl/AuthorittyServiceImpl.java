package com.gogo.uidon.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogo.uidon.dao.admin.AuthorityDao;
import com.gogo.uidon.entity.admin.Authority;
import com.gogo.uidon.service.admin.AuthorittyService;
@Service
public class AuthorittyServiceImpl implements AuthorittyService{
	@Autowired
	private AuthorityDao athdao;
	@Override
	public int add(Authority ath) {
		// TODO Auto-generated method stub
		return athdao.add(ath);
	}

	@Override
	public int deleteByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return athdao.deleteByRoleId(roleId);
	}

	@Override
	public List<Authority> findByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return athdao.findByRoleId(roleId);
	}

}
