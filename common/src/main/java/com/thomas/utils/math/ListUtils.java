package com.thomas.utils.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 3/17/17.
 */
public class ListUtils {
    public static void add(List<Double> list1, List<Double> list2) {
        if (list1.size() == list2.size()) {
            for (int i=0; i<list1.size(); i++) list1.set(i, list1.get(i) + list2.get(i));
        }
    }

    public static List<Double> addList(List<Double> list1, List<Double> list2) {
        List<Double> list = new ArrayList<Double>();
        if (list1.size() == list2.size()) {
            for (int i=0; i<list1.size(); i++) list.add(list1.get(i) + list2.get(i));
        }
        return list;
    }

    public static ArrayList<Double> init(int size, double value) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (int i=0; i<size; i++) list.add(value);
        return list;
    }
}
