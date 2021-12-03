package com.edn.base.strategytest.strategy.impl;

import com.edn.base.strategytest.strategy.BankStrategy;
import com.edn.base.strategytest.strategy.StrategyType;
import org.springframework.stereotype.Service;

@Service
public class DefaultBankStrategy implements BankStrategy {

    @Override
    public StrategyType getStrategy() {
        return null;
    }

    @Override
    public String executeBankProcess() {
        return "default bank process has been started";
    }

}
