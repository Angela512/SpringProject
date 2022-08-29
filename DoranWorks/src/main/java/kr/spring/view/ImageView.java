package kr.spring.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class ImageView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte[] file = (byte[])model.get("imageFile");
		String filename = (String)model.get("filename"); //DB에서 뽑아냄
		
		//확장자 체크								file.gif 이런거?
		String ext = filename.substring(filename.lastIndexOf("."));
		if(ext.equalsIgnoreCase(".gif")) {
			ext = "image/gif";
		}else if(ext.equalsIgnoreCase(".png")) {
			ext = "image/png";
		}else {
			ext = "image/jpeg";
		}
		
		response.setContentType(ext);
		response.setContentLength(file.length);
		
		String fileName = new String(filename.getBytes("utf-8"),"iso-8859-1");	
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		InputStream input = null;
		try {
			input = new ByteArrayInputStream(file);
			IOUtils.copy(input, out);
			out.flush();
		}finally {
			if(out != null) out.close();
			if(input != null) input.close();
		}
	}

}





