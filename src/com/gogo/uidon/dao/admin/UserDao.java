package com.gogo.uidon.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gogo.uidon.entity.admin.User;
/**
 * user 用户dao
 * @author Administrator
 *
 */
@Repository
public interface UserDao {
	public User findByUsername(String username);
	public int add(User user);
	public int edit(User user);
	public int delete(String ids);
	public int editPassword(User user);
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
