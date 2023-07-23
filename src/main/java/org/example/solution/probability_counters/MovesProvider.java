package org.example.solution.probability_counters;

import java.util.stream.Stream;

public final class MovesProvider {
    private MovesProvider() {

    }

    public static Stream<ChessmanPosition> knightMoves(ChessmanPosition currentPosition) {
        return Stream.of(-2, -1, 1, 2).flatMap(horizontalDifference -> {
            var verticalDifferenceAbsolute = Math.abs(horizontalDifference) == 1 ? 2 : 1;
            return Stream.of(verticalDifferenceAbsolute, -verticalDifferenceAbsolute)
                    .map(verticalDifference -> new ChessmanPosition(
                            currentPosition.row() + horizontalDifference,
                            currentPosition.column() + verticalDifference));
        });
    }

    public static Stream<ChessmanPosition> kingMoves(ChessmanPosition currentPosition) {
        return Stream.of(-1, 0, 1).flatMap(horizontalOffset ->
                Stream.of(-1, 0, 1)
                        .filter(verticalOffset -> horizontalOffset != 0 || verticalOffset != 0)
                        .map(verticalOffset -> new ChessmanPosition(
                                currentPosition.row() + horizontalOffset,
                                currentPosition.column() + verticalOffset)));
    }

    public static Stream<ChessmanPosition> pawnMoves(ChessmanPosition currentPosition) {
        return Stream.of(-1, 0, 1).map(horizontalOffset -> new ChessmanPosition(
                currentPosition.row() + horizontalOffset,
                currentPosition.column() - 1
        ));
    }
}
