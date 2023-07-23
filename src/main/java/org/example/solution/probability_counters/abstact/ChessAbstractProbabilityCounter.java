package org.example.solution.probability_counters.abstact;

import org.example.solution.probability_counters.ChessmanPosition;

import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ChessAbstractProbabilityCounter<V> extends ChessGeneralProbabilityCounter<V> {
    protected ChessAbstractProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    protected abstract Stream<ChessmanPosition> provideMoves(ChessmanPosition currentPosition);

    protected abstract V addProbabilities(V probability1, V probability2);

    protected abstract V multiplyProbabilities(V probability1, V probability2);

    protected abstract V denominatorMultiplier();

    protected abstract V getEmptyProbability();

    @Override
    public final Map<ChessmanPosition, V> countKnightProbability(int movesLimit) {
        UnaryOperator<Map<ChessmanPosition, V>> recalculateProbabilities = previousBoard -> availablePositions
                .stream().collect(Collectors.toMap(position -> position, position -> provideMoves(position)
                        .filter(this::containsPosition)
                        .map(previousBoard::get)
                        .map(probability -> multiplyProbabilities(probability, denominatorMultiplier()))
                        .reduce(getEmptyProbability(), this::addProbabilities)));
        return Stream.iterate(initialProbabilityBoard, recalculateProbabilities)
                .limit(movesLimit + 1)
                .reduce((previous, current) -> current)
                .orElseThrow();
    }
}
