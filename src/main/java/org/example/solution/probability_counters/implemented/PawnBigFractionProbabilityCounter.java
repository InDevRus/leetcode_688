package org.example.solution.probability_counters.implemented;

import org.apache.commons.math3.fraction.BigFraction;
import org.example.solution.probability_counters.ChessmanPosition;
import org.example.solution.probability_counters.MovesProvider;
import org.example.solution.probability_counters.abstact.ChessBigFractionProbabilityCounter;

import java.util.stream.Stream;

@SuppressWarnings("unused")
public class PawnBigFractionProbabilityCounter extends ChessBigFractionProbabilityCounter {
    public PawnBigFractionProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Stream<ChessmanPosition> provideMoves(ChessmanPosition currentPosition) {
        return MovesProvider.pawnMoves(currentPosition);
    }

    @Override
    protected BigFraction denominatorMultiplier() {
        return new BigFraction(1, 8);
    }
}
