package org.example.solution.probability_counters.abstact;

import org.example.solution.probability_counters.ChessmanPosition;

import java.util.Map;

public interface ChessProbabilityCounter<V> {
    Map<ChessmanPosition, V> countKnightProbability(int movesLimit);
}
