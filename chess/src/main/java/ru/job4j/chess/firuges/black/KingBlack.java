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
public class KingBlack implements Figure {
    private final Cell position;
    private final boolean wasMoved;
    public KingBlack(final Cell position, boolean wasMoved) {
        this.wasMoved = wasMoved;
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isCorrect(source, dest)) {
            throw new ImpossibleMoveException("King can't move so");
        }
        return new Cell[] {source, dest};
    }

    private boolean isCorrect(Cell source, Cell dest) {
        int dX = dest.x - source.x;
        int dY = dest.y - source.y;
        return dX >= -1 && dX <= 1 && dY >= -1 && dY <= 1;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest, false);
    }
}
