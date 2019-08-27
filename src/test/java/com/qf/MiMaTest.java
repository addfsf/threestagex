package com.qf;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.assertj.core.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MiMaTest {

    @Test
    public void tm(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();

            System.out.println(simpleDateFormat.format(date));


    }

    @Test
    public void test1(){
    //数据源
    String soucex="1234";
    //盐
    String salt="ee";
    //加墨迭代次数
    int d=1024;
    //加密方法
    Md5Hash md5Hash=new Md5Hash(soucex,salt,d);
        soucex=md5Hash.toHex();
        System.out.println(soucex);
    }
}