package ru.job4j.search;

import java.util.Iterator;
import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Insert tasks in list in oder depended on priority of the task
     * @param task adding task
     */
    public void put(Task task) {

        Iterator i = this.tasks.iterator();
        if (this.tasks.isEmpty()) {
            this.tasks.add(task);
        } else {
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).getPriority() > task.getPriority()) {
                    tasks.add(j, task);
                    break;
                } else if (j == tasks.size() - 1) {
                    tasks.addLast(task);
                    break;
                }
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
