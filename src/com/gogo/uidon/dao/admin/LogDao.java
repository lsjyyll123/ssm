package com.gogo.uidon.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gogo.uidon.entity.admin.Log;

/**
 *日志dao
 * @author Administrator
 *
 */
@Repository
public interface LogDao {
	public int add(Log log);
	public int add(String content);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
