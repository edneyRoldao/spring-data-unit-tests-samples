package com.edn.base;

import com.edn.base.strategytest.factory.BankStrategyFactory;
import com.edn.base.strategytest.strategy.BankStrategy;
import com.edn.base.strategytest.strategy.StrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseApplication implements CommandLineRunner {

	@Autowired
	private BankStrategyFactory factory;

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BankStrategy strategy = factory.getStrategy(StrategyType.BRAZIL_BANK);
		String result = strategy.executeBankProcess();

		System.out.println("#######################");
		System.out.println("#######################");
		System.out.println(result);
		System.out.println("#######################");
		System.out.println("#######################");
	}

}
