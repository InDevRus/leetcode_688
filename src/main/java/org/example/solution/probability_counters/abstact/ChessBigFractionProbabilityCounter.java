package org.example.solution.probability_counters.abstact;

import org.apache.commons.math3.fraction.BigFraction;

public abstract class ChessBigFractionProbabilityCounter extends ChessAbstractProbabilityCounter<BigFraction> {
    protected ChessBigFractionProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected BigFraction addProbabilities(BigFraction probability1, BigFraction probability2) {
        return probability1.add(probability2);
    }

    @Override
    protected BigFraction multiplyProbabilities(BigFraction probability1, BigFraction probability2) {
        return probability1.add(probability2);
    }

    @Override
    protected BigFraction getEmptyProbability() {
        return BigFraction.ZERO;
    }

    @Override
    protected BigFraction getInitialProbability() {
        return BigFraction.ONE;
    }
}

