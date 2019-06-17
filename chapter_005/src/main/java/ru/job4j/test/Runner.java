package ru.job4j.test;

import java.util.*;

public class Runner {

    /**
     * Returns the order of running scripts
     *
     * @param ds script dependencies.
     * @param scriptId id of load script
     */
    public List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        var result = load(ds, scriptId, new LinkedHashSet<>());
        return new ArrayList<>(result);
    }

    private Set<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId, Set<Integer> list) {
        List<Integer> dependencies = ds.get(scriptId);
        if (dependencies != null) {
            for (int id :dependencies) {
                this.load(ds, id, list);
                list.add(id);
            }
        }
        return list;
    }
}
