package com.qf.controller;

import lombok.Value;

public abstract class PayBaseController{

        // 支付宝支付参数配置 //

        protected String app_id = "111";//应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号

        protected String merchant_private_key;//商户私钥，您的PKCS8格式RSA2私钥

        protected String alipay_public_key;//支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。

        protected String notify_url;//服务器异步通知页面路径

        protected String return_url;//页面跳转同步通知页面路径

        protected String sign_type = "RSA2";//签名方式
        protected String charset = "utf-8";//字符编码格式

        protected String gateway_url;//支付宝网关

        // 微信支付参数配置 //

        protected String APPID;//公众账号ID

        protected String MCHID;//微信支付商户号

        protected String KEY;//API密钥

        protected String APPSECRET;//AppSecret是APPID对应的接口密码

        protected String NOTIFY_URL;//回调地址。测试回调必须保证外网能访问到此地址

        protected String CREATE_IP;//发起请求的电脑IP

    }

