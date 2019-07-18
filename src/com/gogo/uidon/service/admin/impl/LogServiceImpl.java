package com.gogo.uidon.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogo.uidon.dao.admin.LogDao;
import com.gogo.uidon.entity.admin.Log;
import com.gogo.uidon.service.admin.LogService;

import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;

	@Override
	public int add(Log log) {
		return logDao.add(log);
	}

	@Override
	public int add(String content) {
		return logDao.add(content);
	}

	@Override
	public List<Log> findList(Map<String, Object> queryMap) {
		return logDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return logDao.getTotal(queryMap);
	}

	@Override
	public int delete(String ids) {
		return logDao.delete(ids);
	}

	 
	
	

}
