package org.example.solution.probability_counters.abstact;

import org.example.solution.probability_counters.ChessmanPosition;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ChessGeneralProbabilityCounter<V> implements ChessProbabilityCounter<V> {
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
