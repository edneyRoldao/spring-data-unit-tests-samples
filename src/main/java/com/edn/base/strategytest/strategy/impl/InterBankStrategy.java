package com.edn.base.strategytest.strategy.impl;

import com.edn.base.strategytest.strategy.BankStrategy;
import com.edn.base.strategytest.strategy.StrategyType;
import org.springframework.stereotype.Service;

@Service
public class InterBankStrategy implements BankStrategy {

    @Override
    public StrategyType getStrategy() {
        return StrategyType.INTER_BANK;
    }

    @Override
    public String executeBankProcess() {
        return "inter bank process has been started";
    }

}
