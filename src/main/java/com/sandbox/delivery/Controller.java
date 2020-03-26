package com.sandbox.delivery;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sandbox.delivery.utilities.FileUploadController;

@org.springframework.stereotype.Controller	
public class Controller {

	@Autowired
	private FileUploadController fileUploadController;

	@GetMapping(path = "/createdata/")
	public String createDB() {
		fileUploadController.setLoadDataFilename("Export_CLIE.txt");
		try {
			fileUploadController.loadData(fileUploadController.getLoadDataFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Base de données remplie";
	}
	
	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "home";
	}
}
