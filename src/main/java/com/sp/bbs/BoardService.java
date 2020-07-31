package com.sp.bbs;

import java.util.List;
import java.util.Map;

public interface BoardService {
	public void insertBoard(Board dto, String pathName) throws Exception;
	public void updateBoard(Board dto, String pathName) throws Exception;
	public void deleteBoard(int num, String pathName, String userId) throws Exception;
	public Board readBoard(int num);
	public Board preReadBoard(Map<String, Object> map);
	public Board nextReadBoard(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public void updateHitCount(int num) throws Exception;
	public List<Board> listBoard(Map<String, Object> map);
}
