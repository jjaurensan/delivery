package com.sandbox.delivery.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.services.AddressService;
import com.sandbox.delivery.services.CustomerService;

import jdk.internal.jline.internal.InputStreamReader;

@Component
public class FileUploadController {
	
	@Autowired
	CustomerService customerService;
	
	private String loadDataFilename;
	
	public void setLoadDataFilename(String loadDataFilename) {
		this.loadDataFilename = loadDataFilename;
	}
	
	public String getLoadDataFilename() {
		return loadDataFilename;
	}
	
	public void loadData(String nameFile) throws IOException {
		String line;
		try (BufferedReader myReader = new BufferedReader(new FileReader(nameFile))) {

			while ((line = myReader.readLine()) != null) {
				String splitLine[] = line.split(";");
				List<Address> addressList = new ArrayList<>();
				//0Code	1FacNom	2FacAdr	3FacSuiteAdr	4FacCp	5FacVille	6Expr1006	7FacInterloc	8LivNom	9LivCivilite	10LivAdr	11LivSuiteAdr	12LivCp	13LivVille	14FacTel	15LivPortable	16LivInterloc
				addressList.add(new Address(splitLine[10], splitLine[11], "", splitLine[12], splitLine[13], false));
				customerService.create(new Customer(splitLine[0], addressList, splitLine[6], splitLine[1], false));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
