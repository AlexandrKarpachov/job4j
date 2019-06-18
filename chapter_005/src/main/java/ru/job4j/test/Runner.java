package ru.job4j.test;

import java.util.*;

public class Runner {

    /**
     * Returns script dependencies
     *
     * @param ds script dependencies.
     * @param scriptId id of load script
     */
    public List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        Queue<Integer> stack = new LinkedList<>(ds.get(scriptId));
        Set<Integer> result = new LinkedHashSet<>();
        while (!stack.isEmpty()) {
            int sc = stack.poll();
            result.add(sc);
            List<Integer> dependencies = ds.get(sc);
            if (dependencies != null) {
                stack.addAll(dependencies);
            }
        }

        return new ArrayList<>(result);
    }

}
