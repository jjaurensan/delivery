package com.sandbox.delivery.contoller;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.delivery.utilities.MonthAmountStat;
import com.sandbox.delivery.utilities.Statistic;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class StatistiqueController{
	
	@Autowired
	private Statistic statistic; 
			
		@GetMapping(value = "/stat/{monthNumber}")
		public double getStatTotalMonth(@PathVariable int monthNumber) {
			return statistic.getTotalAmountOfDeliveryForMonth(Month.of(monthNumber));
		}
		@GetMapping(value = "/stat/year/{year}")
		public List<MonthAmountStat> getStatYear(@PathVariable int year) {
			return statistic.getTotalAmountOfDeliveryForYear(Year.of(year));
		}

}
