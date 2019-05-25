package com.hie2j.bmob_study;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    private Button btn_register;
    private Button btn_login;
    private Button btn_info;
    private Button btn_add;
    private Button btn_query;
    private Button btn_del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtns();
    }

    private void initBtns() {
        btn_info = findViewById(R.id.btn_info);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);
        btn_query = findViewById(R.id.btn_query);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName("罗安鹏5");
                student.setAge(18);
                student.setProfession("软件");
                student.setScore(70);
                student.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null){
                            toast("保存成功 ");
                        }else{
                            toast("保存失败 " + e.getMessage());
                        }
                    }
                });
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
                studentBmobQuery.addWhereEqualTo("profession","软件");
                studentBmobQuery.addWhereEqualTo("age",20);
                studentBmobQuery.findObjects(new FindListener<Student>() {
                    @Override
                    public void done(List<Student> list, BmobException e) {
                        if (e == null) {
                            for (Student student : list) {
                                student.delete(new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null){
                                            toast("删除成功 ");
                                        }else{
                                            toast("删除失败 " + e.getMessage());
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
                studentBmobQuery.addWhereEqualTo("profession","软件");
                studentBmobQuery.addWhereEqualTo("age",18);
                studentBmobQuery.findObjects(new FindListener<Student>() {
                    @Override
                    public void done(List<Student> list, BmobException e) {
                        if (e == null){
                            StringBuffer stringBuffer = new StringBuffer();
                            for (Student student : list){
                                stringBuffer.append(student.toString()).append("\n");
                            }
                            toast(stringBuffer.toString());
                        }else{
                            toast(e.toString());
                        }
                    }
                });
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                useBmobUserRegister();
                useMyUserRegister();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername("王源");
                bmobUser.setPassword("123456");
                bmobUser.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null){
                            toast("登陆成功 " + bmobUser.getUsername());
                        }else{
                            toast("登陆失败 " + e.getMessage());
                        }
                    }
                });
            }
        });
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
                myUser.setSex("男");
                myUser.setAge(18);
                myUser.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
                            toast("更新成功 ");
                        }else{
                            toast("更新失败 " + e.getMessage());
                        }
                    }
                });
            }
        });
    }

    private void useMyUserRegister() {
        MyUser myUser = new MyUser();
        myUser.setUsername("王晶");
        myUser.setPassword("88888888");
        myUser.setAge(64);
        myUser.setSex("男");
        myUser.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null){
                    toast("注册成功 " + myUser.getUsername());
                }else{
                    toast("注册失败 " + e.getMessage());
                }
            }
        });
    }

    private void useBmobUserRegister(){
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername("王源");
        bmobUser.setPassword("123456");
        bmobUser.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null){
                    toast("注册成功 " + bmobUser.getUsername());
                }else{
                    toast("注册失败 " + e.getMessage());
                }
            }
        });
    }
    private void toast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
