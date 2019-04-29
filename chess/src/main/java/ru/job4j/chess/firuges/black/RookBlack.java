package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack implements Figure {
    private final Cell position;
    private final boolean wasMoved;
    public RookBlack(final Cell position, boolean wasMoved) {
        this.wasMoved = wasMoved;
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isLine(source, dest)) {
            throw new ImpossibleMoveException("Rook can't move so");
        }
        int[] deltas = deltas(source, dest);
        int dX = deltas[0];
        int dY = deltas[1];
        int size = dest.x - source.x != 0
                ? Math.abs(dest.x - source.x) + 1 : Math.abs(dest.y - source.y) + 1;
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            steps[i] = Cell.values()[source.y + i * dY + 8 * (source.x + i * dX)];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest, false);
    }

    boolean isLine(Cell source, Cell dest) {
        return dest.x == source.x || dest.y == source.y;
    }

    int[] deltas(Cell source, Cell dest) {
        int dX = 0, dY = 0;
        if (dest.x - source.x != 0) {
            dX = dest.x - source.x > 0 ? 1 : -1;
        } else {
            dY = dest.y - source.y > 0 ? 1 : -1;
        }
        return new int[] {dX, dY};
    }
}
