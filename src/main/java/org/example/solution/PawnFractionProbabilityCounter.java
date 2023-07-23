package org.example.solution;

import org.apache.commons.math3.fraction.Fraction;

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
