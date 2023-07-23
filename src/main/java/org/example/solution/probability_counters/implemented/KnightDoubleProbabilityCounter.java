package org.example.solution.probability_counters.implemented;

import org.example.solution.probability_counters.abstact.ChessDoubleProbabilityCounter;
import org.example.solution.probability_counters.ChessmanPosition;
import org.example.solution.probability_counters.MovesProvider;

import java.util.stream.Stream;

public class KnightDoubleProbabilityCounter extends ChessDoubleProbabilityCounter {
    public KnightDoubleProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Stream<ChessmanPosition> provideMoves(ChessmanPosition currentPosition) {
        return MovesProvider.knightMoves(currentPosition);
    }

    @Override
    protected Double denominatorMultiplier() {
        return 0.125;
    }
}
