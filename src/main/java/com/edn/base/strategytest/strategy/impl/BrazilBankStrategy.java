package com.edn.base.strategytest.strategy.impl;

import com.edn.base.strategytest.strategy.BankStrategy;
import com.edn.base.strategytest.strategy.StrategyType;
import org.springframework.stereotype.Service;

@Service
public class BrazilBankStrategy implements BankStrategy {

    @Override
    public StrategyType getStrategy() {
        return StrategyType.BRAZIL_BANK;
    }

    @Override
    public String executeBankProcess() {
        return "brazil bank process has been started";
    }

}
