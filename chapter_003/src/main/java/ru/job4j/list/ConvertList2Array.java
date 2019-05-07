package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells =
                list.size() % rows > 0 ? list.size() / rows  + 1 : list.size() / rows;
        int[][] array = new int[rows][cells];
        int i = 0;
        int j = 0;
        for(Integer num : list){
            array[j][i++] = num;
            if(i == cells) {
                j++;
                i = 0;
            }
        }
        return array;
    }
}