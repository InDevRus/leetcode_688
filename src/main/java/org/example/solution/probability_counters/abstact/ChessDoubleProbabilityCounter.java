package org.example.solution.probability_counters.abstact;

public abstract class ChessDoubleProbabilityCounter extends ChessAbstractProbabilityCounter<Double> {
    protected ChessDoubleProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Double addProbabilities(Double probability1, Double probability2) {
        return probability1 + probability2;
    }

    @Override
    protected Double multiplyProbabilities(Double probability1, Double probability2) {
        return probability1 * probability2;
    }

    @Override
    protected Double getEmptyProbability() {
        return 0.0;
    }

    @Override
    protected final Double getInitialProbability() {
        return 1.0;
    }
}
