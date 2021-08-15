package com.dragon.talon.algorithm.demo;

import javafx.scene.chart.Chart;

import java.util.*;

/**
 * @ClassName CharSort
 * @Version 1.0
 * @Author dragon
 * @Date 2021/8/14 5:04 下午
 * @Description:无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 **/
public class CharSort {

    public static void main(String[] args) {
        final CharSort charSort = new CharSort();
        String[] result= charSort.charCombina("ab");
        System.out.println(Arrays.toString(result));

    }

    private int length;

    public String[] charCombina(String str) {
        if (null == str || "" == str) {
            return null;
        }
        final char[] element = str.toCharArray();
        this.length = element.length;
        final List<String> combina = combina(element);

        return combina.toArray(new String[combina.size()]);
    }


    public List<String> combina(char[] element) {
        int index = 0;
        List<String> sbList = new ArrayList<>();
        while (index < length) {
            if (element[index] == 0) {
                index++;
                continue;
            }
            final char temp = element[index];
            element[index] = 0;
            final List<String> combina = combina(element);
            int i = 0;
            if (combina.isEmpty()) {
                final StringBuilder sb = new StringBuilder();
                sb.append(temp);
                sbList.add(sb.toString());
            } else {
                for (String str : combina) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(temp);
                    sb.append(str);
                    sbList.add(sb.toString());
                }
            }


            element[index] = temp;
            index++;
        }
        return sbList;
    }
}

