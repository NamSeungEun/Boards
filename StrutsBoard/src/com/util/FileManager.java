package com.util;

import java.io.BufferedInputStream;  //아이바티스 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	
	//파일 업로드 
	public static String doFileUpload(File file, String originalFileName, String path) throws IOException {
		
		String newFileName = null;
		
		if(file==null) {
			return null;
		}
		
		if(originalFileName.equals("")) {
			return null;
		}
		
		//확장자 추출(abc.txt) 점에서 끝까지 추출해서 빼 
		String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		if(fileExt==null || fileExt.equals("")) {
			return null;
		}
		
		//서버에 저장할 새로운 파일 이름 생성
		newFileName = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
		newFileName += System.nanoTime(); //10의 -9승
		newFileName += fileExt;
		
		//파일 업로드
		File f = new File(path);
		
		if(!f.exists()) {  //없으면
			f.mkdirs();   //만들어라 
		}
		
		String fullFilePath = path + File.separator + newFileName;
		
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(fullFilePath);
		
		int data = 0;
		byte[] buffer = new byte[1024];
		
		while((data=fis.read(buffer, 0, 1024))!=-1) {
			
			fos.write(buffer, 0, data);
		}
		fis.close();
		fos.close();
		
		return newFileName;
	}
	
	
	//파일 다운로드                               // 파일 다운 시 필수 
	public static boolean doFileDownload(HttpServletResponse response,
			String saveFileName, String originalFileName, String path) {
		
		try {
			
			String filepath =  path + File.separator + saveFileName;
			
			if (originalFileName==null ||  originalFileName.equals("")) {
				
				originalFileName = saveFileName; 
			}
			
			// 파일을 다운받아 클라이언트 컴에 저장할 때 한글 이름 꺠짐 방지
			originalFileName = 
					new String(originalFileName.getBytes("euc-kr"),"ISO-8859-1"); // 8859_1 만 써줄 수도 있음 
			
			File f = new File(filepath);
			
			if(!f.exists()) {
				return false;
			}
			
			response.setContentType("application/octet-stream"); //윈도우의 기본 표준으로 오켓(.)으로 구성된 파일임을 알려주는 애 
			response.setHeader("Content-disposition", 
					"attachment;fileName="+originalFileName);
			
			//파일을 읽어 내야 함 
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(f));
			
			OutputStream out = response.getOutputStream();
			
			int data;
			byte [] bytes = new byte[4096];
			while((data=bis.read(bytes,0, 4096))!=-1) {
				out.write(bytes,0,data);
			}
			
			out.flush();
			out.close();
			bis.close();
			
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
		return true;
		
		
		
		
	}

	
	
	//파일 삭제 
	public static void doFileDelete(String fileName,String path) {
		
		try {
			
			String filePath = path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists())
				f.delete(); //물리적인 파일 삭제 
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
	
	
}
