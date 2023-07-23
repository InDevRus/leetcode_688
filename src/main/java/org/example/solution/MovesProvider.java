package org.example.solution;

import java.util.stream.Stream;

final class MovesProvider {
    private MovesProvider() {

    }

    static Stream<ChessmanPosition> knightMoves(ChessmanPosition currentPosition) {
        return KnightDoubleProbabilityCounter.knightMoves(currentPosition);
    }

    static Stream<ChessmanPosition> kingMoves(ChessmanPosition currentPosition) {
        return Stream.of(-1, 0, 1).flatMap(horizontalOffset ->
                Stream.of(-1, 0, 1)
                        .filter(verticalOffset -> horizontalOffset != 0 || verticalOffset != 0)
                        .map(verticalOffset -> new ChessmanPosition(
                                currentPosition.row() + horizontalOffset,
                                currentPosition.column() + verticalOffset)));
    }

    static Stream<ChessmanPosition> pawnMoves(ChessmanPosition currentPosition) {
        return Stream.of(-1, 0, 1).map(horizontalOffset -> new ChessmanPosition(
                currentPosition.row() + horizontalOffset,
                currentPosition.column() - 1
        ));
    }
}
