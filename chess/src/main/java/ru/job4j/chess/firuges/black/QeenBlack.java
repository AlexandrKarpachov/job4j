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
public class QeenBlack implements Figure {
    private final Cell position;
    private  BishopBlack bishop = new BishopBlack(null);
    private final RookBlack rook = new RookBlack(null, true);

    public QeenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!bishop.isDiagonal(source, dest) && !rook.isLine(source, dest)) {
            throw new ImpossibleMoveException("Qeen can't move so");
        }
        int size = solveSize(source, dest);
        Cell[] steps = new Cell[size];
        int[] deltas = solveDeltas(source, dest);
        int dX = deltas[0];
        int dY = deltas[1];
        for (int i = 0; i < size; i++) {
            steps[i] = Cell.values()[(source.y + i * dY) + 8 * (source.x + i * dX)];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QeenBlack(dest);
    }

    private int solveSize(Cell source, Cell dest) {
        int size;
        if (bishop.isDiagonal(source, dest)) {
            size = Math.abs(dest.x - source.x) + 1;
        } else {
            size = dest.x - source.x != 0
                    ? Math.abs(dest.x - source.x) + 1 : Math.abs(dest.y - source.y) + 1;
        }
        return size;
    }

    private int[] solveDeltas(Cell source, Cell dest) {
        int[] result;
        if (bishop.isDiagonal(source, dest)) {
            result = bishop.deltas(source, dest);
        } else {
            result = rook.deltas(source, dest);
        }
        return result;
    }
}
