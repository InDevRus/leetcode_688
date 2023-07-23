package org.example.solution.probability_counters.implemented;

import org.apache.commons.math3.fraction.Fraction;
import org.example.solution.probability_counters.abstact.ChessFractionProbabilityCounter;
import org.example.solution.probability_counters.ChessmanPosition;
import org.example.solution.probability_counters.MovesProvider;

import java.util.stream.Stream;

@SuppressWarnings("unused")
public class PawnFractionProbabilityCounter extends ChessFractionProbabilityCounter {
    public PawnFractionProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Stream<ChessmanPosition> provideMoves(ChessmanPosition currentPosition) {
        return MovesProvider.pawnMoves(currentPosition);
    }

    @Override
    protected Fraction denominatorMultiplier() {
        return Fraction.ONE_THIRD;
    }
}
