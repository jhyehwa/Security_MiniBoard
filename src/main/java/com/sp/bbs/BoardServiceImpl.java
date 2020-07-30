package com.sp.bbs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.FileManager;
import com.sp.common.dao.CommonDAO;

@Service("bbs.boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private CommonDAO dao;

	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<Board> listBoard(Map<String, Object> map) {
		
		List<Board> list = null;
		
		try {
			list = dao.selectList("bbs.listBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insertBoard(Board dto, String pathName) throws Exception {

		try {
			String saveFileName = fileManager.doFileUpload(dto.getUpload(), pathName);
			if (saveFileName != null) {
				dto.setSaveFileName(saveFileName);
				dto.setOriginalFileName(dto.getUpload().getOriginalFilename());
			}

			dao.insertData("bbs.insertBoard", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateBoard(Board dto, String pathName) throws Exception {

	}

	@Override
	public void deleteBoard(int num, String pathName) throws Exception {

	}

	@Override
	public Board readBoard(int num) {

		return null;
	}

	@Override
	public Board preReadBoard(Map<String, Object> map) {

		return null;
	}

	@Override
	public Board nextReadBoard(Map<String, Object> map) {

		return null;
	}

	@Override
	public int dataCount(Map<String, Object> map) {

		return 0;
	}

	@Override
	public void updateHitCount(int num) throws Exception {

	}

}
