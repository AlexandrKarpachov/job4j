package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Insert tasks in list in oder depended on priority of the task
     * @param task adding task
     */
    public void put(Task task) {
        int j = 0;
        for (; j < tasks.size(); j++) {
            if (tasks.get(j).getPriority() > task.getPriority()) {
                break;
            }
        }
        tasks.add(j, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
