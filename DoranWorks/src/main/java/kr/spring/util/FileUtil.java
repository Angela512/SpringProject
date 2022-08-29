package kr.spring.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	public static byte[] getBytes(String path) { //절대경로
		FileInputStream fis = null;
		byte[] readbyte = null; //byte배열로 반환해줌
		try {
			fis = new FileInputStream(path);
			readbyte = new byte[fis.available()];
			fis.read(readbyte); //읽어서 readbyte에 보관
		}catch(Exception e) {
			logger.error(e.toString());
		}finally {
			if(fis != null) try{fis.close();} catch(IOException e) {}
		}
		return readbyte;
	}
}
