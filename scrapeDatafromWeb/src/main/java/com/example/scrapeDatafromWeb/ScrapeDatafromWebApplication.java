package com.example.scrapeDatafromWeb;

import com.example.scrapeDatafromWeb.service.ScrapeDataDOJI;
import com.example.scrapeDatafromWeb.service.ScrapeDataPNJ;
import com.example.scrapeDatafromWeb.service.ScrapeDataSJC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrapeDatafromWebApplication implements CommandLineRunner {

	private final ScrapeDataDOJI scrapeDataDOJI;
	private final ScrapeDataSJC scrapeDataSJC;
	private final ScrapeDataPNJ scrapeDataPNJ;

	// Constructor injection
	public ScrapeDatafromWebApplication(ScrapeDataDOJI scrapeDataDOJI, ScrapeDataSJC scrapeDataSJC, ScrapeDataPNJ scrapeDataPNJ) {
		this.scrapeDataDOJI = scrapeDataDOJI;
		this.scrapeDataSJC = scrapeDataSJC;
		this.scrapeDataPNJ = scrapeDataPNJ;
	}

	public static void main(String[] args) {
		SpringApplication.run(ScrapeDatafromWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		scrapeDataDOJI.scrapeGoldPrices();
		scrapeDataSJC.scrapeData();
		scrapeDataPNJ.scrapeGoldPrices();
	}
}
