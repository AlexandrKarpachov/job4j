package ru.job4j.test;

import java.util.*;

public class Departments  {
    public static final class Org implements Comparable<Org> {
        private final List<String> deps;

        public Org(List<String> deps) {
            this.deps = deps;
        }

        private List<String> getDepartments() {
            return deps;
        }

        @Override
        public int compareTo(Org o) {
            List<String> other = o.getDepartments();
            int result = 0;
            int length = this.deps.size() > other.size()
                    ? other.size() : this.deps.size();
            for (int i = 0; i < length; i++) {
                result = this.deps.get(i).compareTo(other.get(i));
                if (result != 0) {
                    break;
                }
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Org)) {
                return false;
            }
            Org org = (Org) o;
            return deps.equals(org.deps);
        }

        @Override
        public int hashCode() {
            return Objects.hash(deps);
        }

        @Override
        public String toString() {
            return deps.toString();
        }
    }

    /**
     * Method converts department List<String> in
     * department List<Departments.Org>
     * @param deps input list.
     */
    public List<Departments.Org> convert(List<String> deps) {
        Set<Org> set = new LinkedHashSet<>();
        for (String dep : deps) {
            List<List<String>> divided = divide(dep);
            for (List<String> divDep : divided) {
                Org org = new Departments.Org(divDep);
                set.add(org);
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * sorts department list in ascending order
     * @param orgs input list
     * @return sorted list
     */
    public List<Org> sortAsc(List<Org> orgs) {
        Collections.sort(orgs);
        return orgs;
    }


    /**
     * sorts department list in descending order
     * @param orgs input list
     * @return sorted list
     */
    public List<Org> sortDesc(List<Org> orgs) {
        Collections.sort(orgs, Collections.reverseOrder());
        return orgs;
    }

    /**
     * retrieves all the root departments from input tree
     * @param deps input department tree, divided by '/'
     * @return all possible variants of department trees
     */
    private List<List<String>> divide(String deps) {
        List<List<String>> result = new ArrayList<>();
        String[] arr = deps.split("/");
        for (int i = 0; i < arr.length; i++) {
            List<String> dep = Arrays.asList(Arrays.copyOf(arr, i + 1));
            result.add(new ArrayList<String>(dep));
        }
        return result;
    }
}