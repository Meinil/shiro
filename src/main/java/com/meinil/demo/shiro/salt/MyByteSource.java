package com.meinil.demo.shiro.salt;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * @Author Meinil
 * @Version 1.0
 */
public class MyByteSource extends SimpleByteSource implements Serializable {
    public MyByteSource(String salt) {
        super(salt);
    }
}
