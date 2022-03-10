package com.meinil.demo.utils;

import java.util.Random;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class SaltUtils {
    private final static Random RANDOM = new Random();
    /**
     * 生成指定位数的salt
     */
    public static String getSalt(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = (char)RANDOM.nextInt(32, 127);
            sb.append(ch);
        }
        return sb.toString();
    }
}
