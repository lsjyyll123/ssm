package com.gogo.uidon.service.admin;

import org.springframework.stereotype.Service;

import com.gogo.uidon.entity.admin.Log;

import java.util.List;
import java.util.Map;

/**
 * 日志接口
 * @author lsj
 *
 */
@Service
public interface LogService {
	public int add(Log log);
	public int add(String content);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
