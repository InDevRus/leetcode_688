package org.example.solution.probability_counters.implemented;

import org.apache.commons.math3.fraction.Fraction;
import org.example.solution.probability_counters.abstact.ChessFractionProbabilityCounter;
import org.example.solution.probability_counters.ChessmanPosition;
import org.example.solution.probability_counters.MovesProvider;

import java.util.stream.Stream;

@SuppressWarnings("unused")
public class KnightFractionProbabilityCounter extends ChessFractionProbabilityCounter {
    public KnightFractionProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Stream<ChessmanPosition> provideMoves(ChessmanPosition currentPosition) {
        return MovesProvider.knightMoves(currentPosition);
    }

    @Override
    protected Fraction denominatorMultiplier() {
        return new Fraction(1, 8);
    }
}
