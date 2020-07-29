package com.sp.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class MyBatisDaoImpl implements CommonDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public int insertData(String id, Object value) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.insert(id, value);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return result;
	}

	@Override
	public int insertData(String id) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.insert(id);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return result;
	}

	@Override
	public int updateData(String id, Object value) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update(id, value);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return result;
	}

	@Override
	public int updateData(String id) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update(id);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return result;
	}

	@Override
	public int deleteData(String id, Object value) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.delete(id, value);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return result;
	}

	@Override
	public int deleteData(String id) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.delete(id);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return result;
	}

	@Override
	public <T> List<T> selectList(String id, Object value) throws Exception {
		List<T> list = null;
		
		try {
			list = sqlSession.selectList(id, value);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return list;
	}

	@Override
	public <T> List<T> selectList(String id) throws Exception {
		List<T> list = null;
		
		try {
			list = sqlSession.selectList(id);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
		
		return list;
	}

	@Override
	public <T> T selectOne(String id, Object value) throws Exception {

		try {
			return sqlSession.selectOne(id, value);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
	}

	@Override
	public <T> T selectOne(String id) throws Exception {
		
		try {
			return sqlSession.selectOne(id);
		} catch (Exception e) {
			logger.error(e.toString());
			
			throw e;
		}
	}

}
