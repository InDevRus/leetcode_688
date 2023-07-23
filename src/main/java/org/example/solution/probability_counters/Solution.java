package org.example.solution.probability_counters;

import org.example.solution.probability_counters.implemented.KnightDoubleProbabilityCounter;

@SuppressWarnings("unused")
public class Solution {
    public double knightProbability(int boardSize, int movesLimit, int row, int column) {
        var probabilityCounter = new KnightDoubleProbabilityCounter(boardSize);
        return probabilityCounter.countKnightProbability(movesLimit).get(new ChessmanPosition(row, column));
    }
}