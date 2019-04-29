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
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Bishop can't move to this sell");
        }
        int size = Math.abs(dest.x - source.x) + 1;
        Cell[] res = new Cell[size];
        int dX = deltas(source, dest)[0];
        int dY = deltas(source, dest)[1];
        for (int i = 0; i < size; i++) {
            res[i] = Cell.values()[(source.y + i * dY) + 8 * (source.x + i * dX)];
        }
        return res;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y);
    }

    int[] deltas(Cell source, Cell dest) {
        int[] result = new int[2];
        result[0] = dest.x - source.x > 0 ? 1 : -1;
        result[1] = dest.y - source.y > 0 ? 1 : -1;
        return result;
    }
}
