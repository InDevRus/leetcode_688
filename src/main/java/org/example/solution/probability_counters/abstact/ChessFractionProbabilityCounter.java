package org.example.solution.probability_counters.abstact;

import org.apache.commons.math3.fraction.Fraction;

@SuppressWarnings("unused")
public abstract class ChessFractionProbabilityCounter extends ChessAbstractProbabilityCounter<Fraction> {
    protected ChessFractionProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Fraction getInitialProbability() {
        return Fraction.ONE;
    }

    @Override
    protected Fraction addProbabilities(Fraction probability1, Fraction probability2) {
        return probability1.add(probability2);
    }

    @Override
    protected Fraction multiplyProbabilities(Fraction probability1, Fraction probability2) {
        return probability1.multiply(probability2);
    }

    @Override
    protected Fraction getEmptyProbability() {
        return Fraction.ZERO;
    }
}
