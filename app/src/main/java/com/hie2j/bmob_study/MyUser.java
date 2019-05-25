package com.hie2j.bmob_study;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
    private int age;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
