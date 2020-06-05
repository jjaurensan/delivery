package com.sandbox.delivery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sandbox.delivery.print.MyPdfGenerator;
import com.sandbox.delivery.services.CustomerService;
import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.CustomerBO;
import com.sandbox.delivery.services.bo.DeliveryBO;

@org.springframework.stereotype.Controller
@CrossOrigin(origins = {"*","http://localhost:4200","http://5and8ox.com:4200"})
public class Controller {

	final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private CustomerService customerService;

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

	@ResponseBody
	@GetMapping("/pdf/{idCarrier}/{stringDate}")
	public ResponseEntity<InputStreamResource> homePageGeneratePDF(Model model,
			@PathVariable("idCarrier") long idCarrier,
			@PathVariable("stringDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stringDate)
			throws FileNotFoundException {
		model.addAttribute("appName", appName);
		model.addAttribute("localDateTime", LocalDateTime.now());
		List<DeliveryBO> listDelivery = deliveryService.findAllByCarrierAndCreateDateDelivery(idCarrier, stringDate);

		try {

			new MyPdfGenerator(stringDate, listDelivery);
		} catch (Exception e) {
			logger.error("context", e);
			logger.error(stringDate.toString(), e);
		}
		String nameFile = stringDate + "_" + listDelivery.get(0).getCarrier().getName()+".pdf";
		logger.warn(nameFile);
		File file = new File("results/pdf/" + nameFile);

		InputStream inputStream = new FileInputStream(file);

		HttpHeaders responseHeaders = new HttpHeaders();
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
		// just in case you need to support browsers
		responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=" + nameFile));
		return new ResponseEntity<>(inputStreamResource, responseHeaders, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/file/{nameFile}")
	public ResponseEntity<InputStreamResource> helloWorld(@PathVariable("nameFile") String nameFile)
			throws FileNotFoundException {
		File file = new File("results/pdf/" + nameFile);
		InputStream inputStream = new FileInputStream(file);
		HttpHeaders responseHeaders = new HttpHeaders();
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
		// just in case you need to support browsers
		responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=" + nameFile));
		return new ResponseEntity<>(inputStreamResource, responseHeaders, HttpStatus.OK);

	}
}
