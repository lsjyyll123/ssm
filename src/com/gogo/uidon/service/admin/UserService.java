package com.gogo.uidon.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gogo.uidon.entity.admin.User;

/**
 * user service
 * @author Administrator
 *
 */
@Service
public interface UserService {
	public User findByUsername(String username);
	public int add(User user);
	public int edit(User user);
	public int delete(String ids);
	public int editPassword(User user);
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	
}
