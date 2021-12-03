package com.edn.base.strategytest.factory;

import com.edn.base.strategytest.strategy.BankStrategy;
import com.edn.base.strategytest.strategy.StrategyType;
import com.edn.base.strategytest.strategy.impl.DefaultBankStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class BankStrategyFactory {

    private final List<BankStrategy> strategies;

    public BankStrategy getStrategy(StrategyType strategyType) {
        return strategies
                .stream()
                .filter(st -> Objects.nonNull(st.getStrategy()) && st.getStrategy().equals(strategyType))
                .findAny()
                .orElseGet(DefaultBankStrategy::new);
    }

}
