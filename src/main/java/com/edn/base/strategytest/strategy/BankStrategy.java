package com.edn.base.strategytest.strategy;

public interface BankStrategy {

    StrategyType getStrategy();

    String executeBankProcess();

}
