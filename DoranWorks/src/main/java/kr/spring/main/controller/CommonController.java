package kr.spring.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CommonController {
	
	@RequestMapping("/common/imageUploader.do")
	@ResponseBody
	public Map<String, Object> uploadImage(MultipartFile upload,
											HttpServletRequest request) throws Exception{
		
		//업로드할 폴더 절대 경로
		String realFolder = request.getServletContext().getRealPath("/image_upload");
		
		//업로드한 파일 이름
		String org_filename = upload.getOriginalFilename();
		//새로운 파일명 (시간 + 업로드한 파일명)
		String str_filename = System.currentTimeMillis()+org_filename;
		
		//파일이 저장될 경로
		String filepath = realFolder+"\\"+str_filename;
		
		File f = new File(filepath);
		if(!f.exists()) {
			//지정한 폴더가 없으면 폴더를 생성하고 생성된 폴더에 파일 저장
			f.mkdirs();
		}
		upload.transferTo(f);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upload", true);
		map.put("url", request.getContextPath()+"/image_upload/"+str_filename);
		
		return map;
	}
}
