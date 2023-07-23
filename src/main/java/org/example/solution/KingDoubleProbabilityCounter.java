package org.example.solution;

import java.util.stream.Stream;

@SuppressWarnings("unused")
public class KingDoubleProbabilityCounter extends ChessDoubleProbabilityCounter {
    public KingDoubleProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Stream<ChessmanPosition> provideMoves(ChessmanPosition currentPosition) {
        return MovesProvider.kingMoves(currentPosition);
    }

    @Override
    protected Double denominatorMultiplier() {
        return 0.125;
    }
}
