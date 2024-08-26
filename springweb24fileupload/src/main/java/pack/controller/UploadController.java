package pack.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@GetMapping("/upload")
	public String abc(UploadFile uploadFile) {
		return "uploadform";
	}
	
	@PostMapping("/upload")
	public String submit(UploadFile uploadFile, Model model, BindingResult result) {	// bindingresult : 에러를 자동으로 잡아줘
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		// 업로드 된 파일 검사
		MultipartFile file = uploadFile.getFile();
		String fileName = file.getOriginalFilename();
		
		if(result.hasErrors()) {	// 파일을 선택하지 않으면 에러가 떨어진다.
			return "err";
		}
		
		try {
			inputStream = file.getInputStream();
			File newFile = new File("D:\\Study\\Academy\\Acorn\\second_half\\springSource\\springweb24fileupload\\src\\main\\resources\\static\\upload\\" + fileName);
			if(!newFile.exists()) {	// 그 파일이 없구나ㅏ
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while((read = inputStream.read(bytes)) != -1) {	// 자료가 있는 동안 읽어~
				outputStream.write(bytes, 0, read);
			}
		} catch (Exception e) {
			System.out.println("file submit err : " + e);
			return "err";
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		model.addAttribute("filename", fileName);
		return "uploadfile";
	}
}
