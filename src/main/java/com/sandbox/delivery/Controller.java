package com.sandbox.delivery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.print.MyPdfGenerator;
import com.sandbox.delivery.services.CustomerService;
import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.utilities.FileUploadController;


@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private FileUploadController fileUploadController;
	@Autowired
	CustomerService customerService;

	@Autowired
	private DeliveryService deliveryService;
	// URL test = getClass().getResource("/templates/Export_CLIE.txt");
	// File file = new File("Export_CLIE.txt");

	@Value("/templates/Export_CLIE.txt")
	private ClassPathResource resource;

	@GetMapping(path = "/createdata/")
	public String createDB() throws IOException {
		String line;
		BufferedReader myReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		while ((line = myReader.readLine()) != null) {
			String splitLine[] = line.split(";");
			List<Address> addressList = new ArrayList<>();
			// 0Code 1FacNom 2FacAdr 3FacSuiteAdr 4FacCp 5FacVille 6Expr1006 7FacInterloc
			// 8LivNom 9LivCivilite 10LivAdr 11LivSuiteAdr 12LivCp 13LivVille 14FacTel
			// 15LivPortable 16LivInterloc
			addressList.add(new Address(splitLine[10], splitLine[11], "", splitLine[12], splitLine[13], false));
			customerService.create(new Customer(splitLine[0], addressList, splitLine[6], splitLine[1], false));

		}
		myReader.close();
		return "Base de données remplie";
	}

//	@GetMapping(path = "/createdata/")
//	public String createDB() {
//		fileUploadController.setLoadDataFilename(test.getFile());
//		//System.out.println("Absoulue : "+file.getAbsolutePath());
//		//System.out.println("path : "+file.getPath());
//		System.out.println("name : "+test.getPath());
//		try {
//			fileUploadController.loadData(fileUploadController.getLoadDataFilename());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "Base de données remplie";
//	}

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		model.addAttribute("localDateTime", LocalDateTime.now());
		try {
			MyPdfGenerator monDoc = new MyPdfGenerator(deliveryService.getAll());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "home";
	}
}
