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

		try {
			String saveFileName = fileManager.doFileUpload(dto.getUpload(), pathName);
			if (saveFileName != null) {
				if (dto.getSaveFileName() != null && dto.getSaveFileName().length() != 0) {
					fileManager.doFileDelete(dto.getSaveFileName(), pathName);
				}

				dto.setSaveFileName(saveFileName);
				dto.setOriginalFileName(dto.getUpload().getOriginalFilename());
			}

			dao.updateData("bbs.updateBoard", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void deleteBoard(int num, String pathName, String userId) throws Exception {

		try {
			Board dto = readBoard(num);
			if (dto == null || (!userId.equals("admin") && !userId.equals(dto.getUserId()))) {
				return;
			}

			fileManager.doFileDelete(dto.getSaveFileName(), pathName);

			dao.deleteData("bbs.deleteBoard", num);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Board readBoard(int num) {

		Board dto = null;

		try {
			dto = dao.selectOne("bbs.readBoard", num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public Board preReadBoard(Map<String, Object> map) {

		Board dto = null;

		try {
			dto = dao.selectOne("bbs.preReadBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public Board nextReadBoard(Map<String, Object> map) {

		Board dto = null;

		try {
			dto = dao.selectOne("bbs.nextReadBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public int dataCount(Map<String, Object> map) {

		int result = 0;

		try {
			result = dao.selectOne("bbs.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void updateHitCount(int num) throws Exception {

		try {
			dao.updateData("bbs.updateHitCount", num);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
