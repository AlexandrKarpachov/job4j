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
public class PawnBlack implements Figure {
    private final Cell position;
    private final boolean wasMoved;
    public PawnBlack(final Cell position, boolean wasMoved) {
        this.position = position;
        this.wasMoved = wasMoved;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        if (!isCorrectMove(source, dest)) {
            throw new ImpossibleMoveException("Pawn can't move so");
        }
        int size = (source.y == dest.y + 2) && !wasMoved ? 3 : 2;
        Cell[] steps = new Cell[size];
        for (int i = 0; i < size; i++) {
            steps[i] = Cell.values()[source.y - i + 8 * (source.x)];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest, true);
    }

    private boolean isCorrectMove(Cell source, Cell dest) {
        boolean result;
        if (wasMoved) {
            result = source.y == dest.y + 1 && source.x == dest.x;
        } else {
            result = (source.y == dest.y + 1 || source.y == dest.y + 2)
                    && source.x == dest.x;
        }
        return result;
    }
}
