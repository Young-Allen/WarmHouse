package me.spring.utils;

import java.util.Stack;

public class Parse {
	 public static int zh2arbaNum(String zhNumStr) {
        Stack<Integer> stack = new Stack<>();
        String numStr = "一二三四五六七八九";
        String unitStr = "十百千万亿";

        String[] ssArr = zhNumStr.split("");
        for (String e : ssArr) {
            int numIndex = numStr.indexOf(e);
            int unitIndex = unitStr.indexOf(e);
            if (numIndex != -1) {
                stack.push(numIndex + 1);
            } else if (unitIndex != -1) {
                int unitNum = (int) Math.pow(10, unitIndex + 1);
                if (stack.isEmpty()) {
                    stack.push(unitNum);
                } else {
                    stack.push(stack.pop() * unitNum);
                }
            }
        }
        return stack.stream().mapToInt(s -> s).sum();
    }
}
