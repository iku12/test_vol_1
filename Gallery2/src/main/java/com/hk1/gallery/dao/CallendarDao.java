package com.hk1.gallery.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk1.gallery.dto.CallendarDto;

@Repository
public class CallendarDao implements ICallendarDao {
	private String namespace = "com.hk1.Callendar.";
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	   @Override
	   public boolean insertCallendar(CallendarDto callendarDto) {
		  int count = sqlSession.insert(namespace+"insertCallendar",callendarDto);
	      return count>0?true:false;
	   }
	   @Override
	   public boolean updateCallendar(CallendarDto callendarDto) {
		   int count = sqlSession.update(namespace+"updateCallendar", callendarDto);
		   return count>0?true:false;
	   }
	   @Override
	   public boolean deleteCallendar(int c_no) {
		   int count = sqlSession.delete(namespace+"deleteCallendar", c_no);
		   return count>0?true:false;
	   }
	   @Override
	   public CallendarDto selectCallendar(int c_no) {
	      
	      return sqlSession.selectOne(namespace+"selectCallendar1",c_no);
	   }
	   @Override
	   public CallendarDto selectCallendarList(int g_no) {
	      
	      return sqlSession.selectOne(namespace+"selectCallendar2", g_no);
	   }
	   @Override
	   public List<CallendarDto> selectCallendarList() {
	      
	      return sqlSession.selectList(namespace+"selectCalendarList");
	   }

}
