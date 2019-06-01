package ru.job4j.generics;
/**
 * {@code AbstractStore} provides functionality of {@param Base} repository
 *
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 01.06.2019
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;

    protected AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    /**
     * adds items to store
     * @param model input item
     */
    @Override
    public void add(T model) {
        this.store.add(model);
    }

    /**
     * replaces item on another item
     * @param id of the item being replaced
     * @param model new item.
     * @return true if operation was successful. Otherwise if
     * item with such id does not exists, returns false.
     */
    @Override
    public boolean replace(String id, T model) {
        int index = this.indexOf(id);
        boolean result = index >= 0;
        if (result) {
            this.store.set(index, model);
        }
        return result;
    }

    /**
     * The method deletes items
     * @param id of the item to be deleting
     * @return true if operation was successful. Otherwise if
     * item with such id does not exists, returns false.
     */
    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        boolean result = index >= 0;
        if (result) {
            this.store.remove(index);
        }
        return result;
    }

    /**
     * The method search items by id
     * @param id of the looking item
     * @return true if operation was successful. Otherwise if
     * item with such id does not exists, returns false.
     */
    @Override
    public T findById(String id) {
        int index = indexOf(id);
        return index >= 0 ? this.store.get(index) : null;
    }

    /**
     * The method looking for an index of item in {@param this.store}
     * @param id of the looking item
     * @return first index with such id,
     * or -1, if item with such id does not exists.
     */
    private int indexOf(String id) {
        int result = 0;
        boolean isFound = false;
        for (Base item : this.store) {
            if (item.getId().equals(id)) {
                isFound = true;
                break;
            }
            result++;
        }
        return isFound ? result : -1;
    }
}
