package ru.job4j.tracker;

import java.util.Date;
import java.util.Objects;

public class Item {
    private String id;
    private String name;
    private String desc;
    private long time;

    public Item(String name, String decs) {
        this.name = name;
        this.desc = decs;
        this.time = new Date().getTime();
    }

    public Item(String name, String decs, long time) {
        this.name = name;
        this.desc = decs;
        this.time = time;
    }

    //region Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
//endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return time == item.time
                && Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(desc, item.desc);
    }

    @Override
    public String toString() {
        return String.format("Item {id = %s, name = %s, desc = %s, time = %s",
                id, name, desc, new Date(time).toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, time);
    }
}