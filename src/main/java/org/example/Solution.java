package org.example;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record ChessmanPosition(int row, int column) {
}

interface ChessProbabilityCounter<V> {
    Map<ChessmanPosition, V> countKnightProbability(int movesLimit);
}

abstract class ChessGeneralProbabilityCounter<V> implements ChessProbabilityCounter<V> {
    protected final int boardSize;
    protected final List<ChessmanPosition> availablePositions;
    protected final Map<ChessmanPosition, V> initialProbabilityBoard;

    protected abstract V getInitialProbability();

    private List<ChessmanPosition> prepareAvailablePositions() {
        return IntStream.range(0, boardSize)
                .mapToObj(row -> IntStream.range(0, boardSize).mapToObj(column -> new ChessmanPosition(row, column)))
                .flatMap(stream -> stream)
                .toList();
    }

    private Map<ChessmanPosition, V> prepareInitialProbabilityBoard() {
        return availablePositions
                .stream()
                .collect(Collectors.toMap(position -> position, position -> getInitialProbability()));
    }

    protected ChessGeneralProbabilityCounter(int boardSize) {
        this.boardSize = boardSize;
        availablePositions = prepareAvailablePositions();
        initialProbabilityBoard = prepareInitialProbabilityBoard();
    }

    protected final boolean containsPosition(ChessmanPosition position) {
        return 0 <= Math.min(position.row(), position.column()) && Math.max(position.row(), position.column()) < boardSize;
    }
}

abstract class ChessAbstractProbabilityCounter<V> extends ChessGeneralProbabilityCounter<V> {
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

abstract class ChessDoubleProbabilityCounter extends ChessAbstractProbabilityCounter<Double> {
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

class KnightDoubleProbabilityCounter extends ChessDoubleProbabilityCounter {
    public KnightDoubleProbabilityCounter(int boardSize) {
        super(boardSize);
    }

    @Override
    protected Stream<ChessmanPosition> provideMoves(ChessmanPosition currentPosition) {
        return Stream.of(-2, -1, 1, 2).flatMap(horizontalDifference -> {
            var verticalDifferenceAbsolute = Math.abs(horizontalDifference) == 1 ? 2 : 1;
            return Stream.of(verticalDifferenceAbsolute, -verticalDifferenceAbsolute)
                    .map(verticalDifference -> new ChessmanPosition(
                            currentPosition.row() + horizontalDifference,
                            currentPosition.column() + verticalDifference));
        });
    }

    @Override
    protected Double denominatorMultiplier() {
        return 0.125;
    }
}

class Solution {
    public double knightProbability(int boardSize, int movesLimit, int row, int column) {
        var probabilityCounter = new KnightDoubleProbabilityCounter(boardSize);
        return probabilityCounter.countKnightProbability(movesLimit).get(new ChessmanPosition(row, column));
    }
}