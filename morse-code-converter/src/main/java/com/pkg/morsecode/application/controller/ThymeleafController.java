package com.pkg.morsecode.application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pkg.morsecode.application.Entity.ConverterRequest;
import com.pkg.morsecode.application.bo.MorseConverterBO;
import com.pkg.morsecode.application.reqres.ConvertMessageRequestTO;
import com.pkg.morsecode.application.reqres.ConvertMessageResponseTO;

@Controller
public class ThymeleafController {

	@Autowired
	MorseConverterBO morseConverterBO;

	@RequestMapping("/")
	public String index(Model model) {
		ConverterRequest converterRequest = new ConverterRequest();
		model.addAttribute("converterRequest", converterRequest);
		model.addAttribute("play", false);
		return "index.html";
	}

	@PostMapping(value = "/convertInput")
	public String convertMessage(ConverterRequest converterRequest, Model model) {
		Boolean play = false;
		if("".equals(converterRequest.getEnglishMessage())) {
			converterRequest.setEnglishMessage(null);
		} else {
			converterRequest.setMorseCode(null);
		}
		final String uri = "http://localhost:8080/convert";

		ConvertMessageRequestTO convertMessageRequestTO = new ConvertMessageRequestTO();
		convertMessageRequestTO.setEnglishMessage(converterRequest.getEnglishMessage());
		convertMessageRequestTO.setMorseCode(converterRequest.getMorseCode());

		RestTemplate restTemplate = new RestTemplate();
		ConverterRequest respose = new ConverterRequest();
		try {
			ResponseEntity<ConvertMessageResponseTO> result = 
					restTemplate.postForEntity(uri, convertMessageRequestTO, ConvertMessageResponseTO.class);

			if(result.getBody().getMorse()) {
				respose.setMorseCode(result.getBody().getMessage());
				respose.setEnglishMessage(converterRequest.getEnglishMessage());
				play = true;
			} else {
				respose.setEnglishMessage(result.getBody().getMessage());
				respose.setMorseCode(converterRequest.getMorseCode()); 
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("converterRequest", respose);
		model.addAttribute("play", play);
		return "index";
	}

	@GetMapping(value = "/convertFile")
	@ResponseBody
	public void convertFileInput(ConverterRequest converterRequest) {

		try {
			String message = "";
			FileInputStream fin = new FileInputStream("E:\\SPARK\\Spring framework\\convert.txt");

			int i=0;    
			while ((i=fin.read()) != -1) {  
				message = message + String.valueOf((char)i);
			}

			System.out.println(message);
			fin.close();

			ConvertMessageRequestTO convertMessageRequestTO = new ConvertMessageRequestTO();
			if(message.contains(".") || message.contains("-")) {
				convertMessageRequestTO.setMorseCode(message);
			}else {
				convertMessageRequestTO.setEnglishMessage(message);
			}

			final String uri = "http://localhost:8080/convert";

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<ConvertMessageResponseTO> response = restTemplate.postForEntity(uri, convertMessageRequestTO, ConvertMessageResponseTO.class); 
			System.out.println(response.getBody().getMessage());

			File file = new File("E:\\SPARK\\Spring framework\\convert_output.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(response.getBody().getMessage().getBytes());

			fos.close();
		}catch(Exception e){

			e.printStackTrace();
		}

	}
	
	@PostMapping(value = "/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, 
					ConverterRequest converterRequest, Model model) {
		
		String filePath = "E:/SPARK/Spring framework/temp.txt";
		
		if(file.isEmpty()) {
			attributes.addFlashAttribute("message","Please select a file to upload !!!");
			return "redirect:/";
		}

		try {

			byte[] bytes = file.getBytes();
			String message = new String(bytes);  

			ConvertMessageRequestTO convertMessageRequestTO = new ConvertMessageRequestTO();
			if(message.contains(".") || message.contains("-")) {
				convertMessageRequestTO.setMorseCode(message);
			}else {
				convertMessageRequestTO.setEnglishMessage(message);
			}

			final String uri = "http://localhost:8080/convert";

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<ConvertMessageResponseTO> response = 
					restTemplate.postForEntity(uri, convertMessageRequestTO, ConvertMessageResponseTO.class); 
			System.out.println(response.getBody().getMessage());
			
			File convertFile = new File(filePath);
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(response.getBody().getMessage().getBytes());
			
			fout.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("readyMessage", "Your File Is Ready To Download !!!");
		model.addAttribute("path", filePath);
		return "index.html";
	}
	
	@GetMapping(value = "/download")
	public @ResponseBody FileSystemResource downloadFile(@RequestParam String path, HttpServletResponse response) {
		
		File file = new File(path);
		response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
	    response.setHeader("Content-Length", String.valueOf(file.length()));
		FileSystemResource fsr = new FileSystemResource(file);
	
		return fsr;
	}
}