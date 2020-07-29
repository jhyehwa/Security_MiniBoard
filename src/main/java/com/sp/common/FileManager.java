package com.sp.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("fileManager")
public class FileManager {
	
	/**
	 * 파일을 업로드 하기 위한 메소드
	 * @param partFile 업로드 할 파일 정보를 가지고 있는 MultipartFile 객체
	 * @param pathname 파일을 저장 할 경로
	 * @return 		     서버에 저장된 새로운 파일의 이름
	 */
	public String doFileUpload(MultipartFile partFile, String pathName) throws Exception {
		String saveFileName = null;
		
		if(partFile == null || partFile.isEmpty()) {
			return null;
		}
		
		// 클라이언트가 업로드한 파일의 이름
		String originalFileName = partFile.getOriginalFilename();
		if(originalFileName == null || originalFileName.length() == 0) {
			return null;
		}
		
		// 확장자
		String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
		if(fileExt == null || fileExt.equals("")) {
			return null;
		}
		
		// 서버에 저장 할 새로운 파일명을 만듬
		saveFileName = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
		saveFileName += System.nanoTime();
		saveFileName += fileExt;
		
		// File.separator : //와 같은 것
		String fullPathName = pathName + File.separator + saveFileName;
		
		// 업로드 할 경로가 존재하지 않는 경우 폴더를 생성
		// getParentFile() : 부모 폴더를 File의 형태로 리튼
		// exists() : 파일의 존재 여부를 리턴
		// mkdirs() : 존재하지 않는 부모 폴더까지 포함하여 해당 경로에 폴더를 만듬
		File f = new File(fullPathName);
		if(!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		
		partFile.transferTo(f); // transferTo() : 파일 객체를 넘겨주면 그 객체의 이름으로 파일 저장
		
		return saveFileName;
	}
	
	/**
	 * 파일을 업로드 하기 위한 메소드
	 * @param bytes 		     업로드 할 파일 정보를 가지고 있는 byte 배열
	 * @param originalFileName 클라이언트가 업로드 한 파일명
	 * @param pathName 		     파일을 저장 할 경로
	 * @return 				     서버에 저장된 새로운 파일의 이름
	 */
	public String doFileUpload(byte[] bytes, String originalFileName, String pathName) throws Exception {
		String saveFileName = null;
		
		if(bytes == null) {
			return null;
		}
		
		// 클라이언트가 업로드 한 파일의 이름
		if(originalFileName == null || originalFileName.length() == 0) {
			return null;
		}
		
		// 확장자
		String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
		if(fileExt == null || fileExt.equals("")) {
			return null;
		}
		
		// 서버에 저장 할 새로운 파일명을 만듬
		saveFileName = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
		saveFileName += System.nanoTime();
		saveFileName += fileExt;
		
		// 업로드할 경로가 존재하지 않는 경우 폴더를 생성
		File dir = new File(pathName);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String fullPathName = pathName + File.separator + saveFileName;
		
		// FileOutputStream : 무조건 해당 파일을 생성한다. 존재하는 파일일 경우 덮어쓰기함.
		FileOutputStream fos = new FileOutputStream(fullPathName);
		fos.write(bytes); // 입력받은 내용을 파일 내용으로 기록
		fos.close(); // 파일을 닫음
		
		return saveFileName;
	}
	
	/**
	 * 파일을 업로드 하기 위한 메소드
	 * @param is			     업로드 할 파일 정보를 가지고 있는 InputStream 객체
	 * @param originalFileName 클라이언트가 업로드 한 파일명
	 * @param pathName		     파일을 저장 할 경로
	 * @return				     서버에 저장된 새로운 파일의 이름
	 */
	public String doFileUpload(InputStream is, String originalFileName, String pathName) throws Exception {
		String saveFileName = null;
		
		// 클라이언트가 업로드한 파일의 이름
		if(originalFileName == null || originalFileName.equals("")) {
			return null;
		}
		
		// 확장자
		String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
		if(fileExt == null || fileExt.equals("")) {
			return null;
		}
		
		// 서버에 저장 할 새로운 파일명을 만듬
		saveFileName = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
		saveFileName += System.nanoTime();
		saveFileName += fileExt;
		
		// 업로드할 경로가 존재하지 않는 경우 폴더를 생성
		File dir = new File(pathName);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String fullPathName = pathName + File.separator + saveFileName;
		
		byte[] b = new byte[1024];
		int size = 0;
		FileOutputStream fos = new FileOutputStream(fullPathName);
		
		while((size = is.read(b)) != -1) {
			fos.write(b, 0, size);
		}
		
		fos.close();
		is.close();
		
		return saveFileName;
	}
	
	/**
	 * 파일을 다운로드하는 메소드
	 * @param saveFileName	     서버에 저장 할 파일 이름
	 * @param originalFileName 클라이언크가 업로드 한 파일 이름
	 * @param pathName		     파일이 저장된 경로
	 * @param response		     응답할 HttpServletResponse 객체
	 * @return				     파일 다운로드 성공 여부
	 */
	public boolean doFileDownload(String saveFileName, String originalFileName, String pathName, HttpServletResponse response) {
		String fullPathName = pathName + File.separator + saveFileName;
		
		try {
			if(originalFileName == null || originalFileName.equals("")) {
				originalFileName = saveFileName;
				originalFileName = new String(originalFileName.getBytes("euc-kr"), "8859_1");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			File file = new File(fullPathName);
			
			if(file.exists()) {
				byte readByte[] = new byte[4096];
				
				response.setContentType("applivation/octet-stream");
				response.setHeader("Content-disposition", "attachment);fileName=" + originalFileName);
				
				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
				OutputStream outs = response.getOutputStream();
				
				int read;
				while((read = fin.read(readByte, 0, 4096)) != -1) {
					outs.write(readByte, 0, read);
				}
				
				outs.flush();
				outs.close();
				fin.close();
				
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}

	/**
	 * 파일들을 압축하는 메소드
	 * @param sources   폴더 명을 포함한 압축 할 파일들
	 * @param originals 압축 할 파일들이 압축 될 때의 파일명
	 * @param pathName  압축 파일을 저장할 경로
	 * @return          압축된 파일명
	 */
	private String fileCompression(String[] sources, String[] originals, String pathName) {
		String archiveFileName = null;
		String fullPathName = null;
		
		final int MAX_SIZE = 2048;
		byte[] buf = new byte[MAX_SIZE];
		String s;
		File f;
		
		ZipOutputStream zos = null;
        FileInputStream fis = null;
        
		try {
			f = new File(pathName);
			if(!f.exists()) {
				f.mkdirs();
			}
			
			archiveFileName = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
			archiveFileName += System.nanoTime() + ".zip";
			fullPathName = pathName + File.separator + archiveFileName;
			
			zos = new ZipOutputStream(new FileOutputStream(fullPathName));
			
			int length;
			for(int idx = 0; idx < sources.length; idx++) {
				fis = new FileInputStream(sources[idx]);
				
				// 압축 파일에 압축되는 파일명
				if(originals != null && originals.length >= idx) {
					s = originals[idx];
				} else {
					s = sources[idx].substring(sources[idx].lastIndexOf(File.separator));
				}
				
				if(s.indexOf(File.separator) == -1) {
					s = File.separator;
				}
				
				zos.putNextEntry(new ZipEntry(s));
				
				length = 0;
				while((length = fis.read(buf)) > 0) {
					zos.write(buf, 0, length);
				}
				
				zos.closeEntry();
				fis.close();
			}
			
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				zos.closeEntry();
				zos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		f = new File(fullPathName);
		if(!f.exists()) {
			return null;
		}
		
		return archiveFileName;
	}
	
	/**
	 * 파일을 삭제하는 메소드
	 * @param pathName 경로를 포함한 삭제 할 파일의 이름
	 */
	public void doFileDelete(String pathName) {
		File file = new File(pathName);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 파일을 삭제하는 메소드
	 * @param fileName 삭제 할 파일의 이름
	 * @param pathName 삭제 할 파일이 존재하는 경로
	 */
	public void doFileDelete(String fileName, String pathName) {
		String fullPathName = pathName + File.separator + fileName;
		File file = new File(fullPathName);
		
		if(file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 파일들을 zip 파일로 압축하여 다운로드하는 메소드
	 * @param sources     폴더명을 포함한 서버에 저장된 압축 할 파일들(경로 포함)
	 * @param originals   압축 할 파일들이 압축될 때의 파일명
	 * @param zipFileName 다운로드할 때 클라이언트에 표시 할 zip 파일명
	 * @param response    응답할 HttpServletResponse 객체
	 * @return            파일 다운로드 성공 여부
	 */
	public boolean doZipFileDownload(String[] sources, String[] originals, String zipFileName, HttpServletResponse response) {
		String pathName = System.getProperty("user.dir") + File.separator + "temp";
		String archiveFileName;
		
		// 파일들을 압축
		archiveFileName = fileCompression(sources, originals, pathName);
		if(archiveFileName == null) {
			return false;
		}
		
		// 파일 다운로드
		boolean b = doFileDownload(archiveFileName, zipFileName, pathName, response);
		
		// 압축한 zip 파일 삭제
		String fullPathName = pathName + File.separator + archiveFileName;
		doFileDelete(fullPathName);
		
		return b;
	}
	
	/**
	 * 파일 또는 폴더 및 하위 폴더를 삭제하는 메소드
	 * @param pathName 삭제 할 파일명(경로 포함) 또는 삭제 할 폴더
	 */
	private void removeSubDirectory(String pathName) {
		File[] listFile = new File(pathName).listFiles();
		
		try {
			if(listFile.length > 0) {
				for(int i = 0; i < listFile.length; i++) {
					if(listFile[i].isDirectory()) {
						removeSubDirectory(listFile[i].getPath());
					}
					listFile[i].delete();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void removePathName(String pathName) {
		
		try {
			File f = new File(pathName);
			if(!f.exists()) {
				return;
			}
			
			if(f.isDirectory()) {
				removeSubDirectory(pathName);
			}
			
			f.delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 파일의 길이를 구하는 메소드
	 * @param pathName 길이를 구할 경로를 포함한 파일 이름
	 * @return   	     파일의 길이
	 */
	public long getFileSize(String pathName) {
		long size = -1;
		
		File file = new File(pathName);
		
		if(!file.exists()) {
			return size;
		}
		
		size = file.length();
		
		return size;
	}
	
	/**
	 * 파일의 타입을 구하는 메소드
	 * @param pahtName 파일 타입을 구할 파일 이름
	 * @return		     파일 타입
	 */
	public String getFileType(String pahtName) {
		String type = "";
		
		try {
			URL u = new URL("file: " + pahtName);
			URLConnection uc = u.openConnection();
			type = uc.getContentType();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return type;
	}
}
