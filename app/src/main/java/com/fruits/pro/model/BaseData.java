package com.fruits.pro.model;

import java.io.Serializable;

/**
 * Created by cwj on 16/7/16.
 */
public class BaseData implements Serializable{
    public boolean error;

    @Override
    public String toString() {
        return "BaseData{" +
                "error=" + error +
                '}';
    }
}
