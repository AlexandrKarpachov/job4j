package ru.job4j.HashMap;

import java.util.*;

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> res = new HashMap<>();
        for(User user : list) {
            res.put(user.getId(), user);
        }
        return res;
    }
}
