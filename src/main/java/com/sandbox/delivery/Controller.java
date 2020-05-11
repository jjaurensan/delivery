package com.sandbox.delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sandbox.delivery.print.MyPdfGenerator;
import com.sandbox.delivery.services.CustomerService;
import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.CustomerBO;

@org.springframework.stereotype.Controller
public class Controller {

	final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	private DeliveryService deliveryService;

	@Value("/templates/Export_CLIE.txt")
	private ClassPathResource resource;

	@GetMapping(path = "/createdata/")
	public String createDB() throws IOException {
		String line;
		BufferedReader myReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		while ((line = myReader.readLine()) != null) {
			String[] splitLine = line.split(";");
			List<AddressBO> addressList = new ArrayList<>();
			// 0Code 1FacNom 2FacAdr 3FacSuiteAdr 4FacCp 5FacVille 6Expr1006 7FacInterloc
			// 8LivNom 9LivCivilite 10LivAdr 11LivSuiteAdr 12LivCp 13LivVille 14FacTel
			// 15LivPortable 16LivInterloc
			addressList.add(new AddressBO(splitLine[10], splitLine[11], "", splitLine[12], splitLine[13], false));
			customerService.create(new CustomerBO(splitLine[0], addressList, splitLine[6], splitLine[1], false));

		}
		myReader.close();
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

	@GetMapping("/pdf/{idCarrier}")
	public String homePageGeneratePDF(Model model,@PathVariable long idCarrier) {
		model.addAttribute("appName", appName);
		model.addAttribute("localDateTime", LocalDateTime.now());
		
		try {
			MyPdfGenerator monDoc;
			 monDoc = new MyPdfGenerator(deliveryService.getAllByIdCarrier(idCarrier));
		} catch (IOException e) {
			logger.error("context", e);
		}
		return "home";
	}
}
