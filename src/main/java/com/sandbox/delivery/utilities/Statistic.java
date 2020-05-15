package com.sandbox.delivery.utilities;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.services.bo.DeliveryBO;

@Component
public class Statistic {

	final Logger logger = LoggerFactory.getLogger(Statistic.class);
	@Autowired
	private DeliveryService deliveryService;

	public long getTotalNumberOfDelivery() {
		return deliveryService.count();
	}

	public double getTotalAmountOfDelivery() {
		double amount = 0.0;
		for (DeliveryBO deliveryBO : deliveryService.getAll()) {
			amount += deliveryBO.getPrice();
		}
		return amount;
	}

	public double getTotalAmountOfDeliveryForDate(LocalDate testDate) {
		double amount = 0.0;
		for (DeliveryBO deliveryBO : deliveryService.findAllByCreateDateDelivery(testDate)) {
				amount += deliveryBO.getPrice();			
		}
		return amount;
	}

	public double getTotalAmountOfDeliveryForMonth(Month month) {
		double amount = 0.0;
		for (DeliveryBO deliveryBO : deliveryService.getAll()) {
			if (deliveryBO.getCreateDateDelivery().getYear() == Year.now().getValue()
					&& deliveryBO.getCreateDateDelivery().getMonth() == month) {
				amount += deliveryBO.getPrice();
			}
		}
		return amount;
	}

	public double getTotalAmountOfDeliveryForMonth(Month month, Year year) {
		double amount = 0.0;
		for (DeliveryBO deliveryBO : deliveryService.getAll()) {
			if (deliveryBO.getCreateDateDelivery().getYear() == year.getValue()
					&& deliveryBO.getCreateDateDelivery().getMonth() == month) {
				amount += deliveryBO.getPrice();
			}
		}
		return amount;
	}
	
	public List<MonthAmountStat> getTotalAmountOfDeliveryForYear(Year year) {
		List<MonthAmountStat> deliveryAmountYear = new ArrayList<>();
		for (int i = 1; i < 13; i++) {

			deliveryAmountYear
					.add(new MonthAmountStat(Month.of(i).name(), getTotalAmountOfDeliveryForMonth(Month.of(i), year)));
		}
		return deliveryAmountYear;
	}
	
}
 
