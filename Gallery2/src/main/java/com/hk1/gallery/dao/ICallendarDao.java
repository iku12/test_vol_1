package com.hk1.gallery.dao;

import java.util.List;

import com.hk1.gallery.dto.CallendarDto;

public interface ICallendarDao {
	boolean insertCallendar(CallendarDto callendarDto);	//U , M
	boolean updateCallendar(CallendarDto callendarDto);	//U , M
	boolean deleteCallendar(int c_no);			//U , M		
	CallendarDto selectCallendar(int c_no);		//U , M
	CallendarDto selectCallendarList(int g_no);		//U , M
	List<CallendarDto> selectCallendarList();		//M
}
