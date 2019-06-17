package ru.job4j.analyze;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        var prev = toMap(previous);
        var curr = toMap(current);
        for (User pUser : prev.values()) {
            var cUser = curr.get(pUser.id);
            if (cUser == null) {
                info.deleted++;
            } else if (!cUser.name.equals(pUser.name)) {
                info.changed++;
            }
            curr.remove(pUser.id);
        }
        info.added = curr.size();
        return info;
    }

    private Map<Integer, User> toMap(List<User> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        User::getId, Function.identity()
                ));
    }

    public Info fastDiff(List<User> previous, List<User> current) {
        Info info = new Info();
        int cursor = 0;
        var pIterator = previous.iterator();
        var cIterator = current.iterator();
        while (pIterator.hasNext() && cIterator.hasNext()) {
            var prev = pIterator.next();
            var curr = current.get(cursor);
            if (prev.id == curr.id) {
                cursor++;
                cIterator.next();
                if (!prev.name.equals(curr.name)) {
                    info.changed++;
                }
            }
        }
        info.deleted = previous.size() - cursor;
        info.added = current.size() - previous.size() + info.deleted;
        return info;
    }

    public static class User {
        private int id;
        private String name;
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public int added() {
            return added;
        }

        public int changed() {
            return changed;
        }

        public int deleted() {
            return deleted;
        }
    }

}
