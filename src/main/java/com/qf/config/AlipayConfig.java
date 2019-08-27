package com.qf.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2019082466475111";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCHlLRQvqCssXiGg2R1slfwoDsAeQ+jEt6JmPEUoyZ1Hhx3wxXKcsG7xk7mOFsr+UkNczCiS5/NXXAHHaF7092E22obYL25nPFfRvBlT1m5YTS60iFbJh15hULvj9MDFAtNvU0GvtwY6B93APtCE8Qj2L2w39kMLuUIvfQmhsIGkIAxs2eRNvFSQtfT2To4x5XTSxouuMveIaHV+JutB9W9hXjWUYGM+2PqTP2PrjRl1hbo5hmTCb8QyElh8EItVtqyfCF239wZLhCaPUlsNwHhkDo/A9aDaORY9+OendBphcjc8LXnIjspa5+UfFmx04XeWBfGYJoH2meKpL47PCapAgMBAAECggEADsPJ8zUfSE83YeazLQIUA5dfdYi5hDfM1k06i9XMnVp/HSe4mshugqVw+C9Q9nP6WaWeuT3lFDdFuPCoIlULPamrX3nkj28g9hBCo7XFfqOwW1wA1+1Jrm8tv+r4vtAS/w9LcR2d2HSG4K3Rrvt6E7FJzxZBi8sblB0jloJ8ZLx1L3PbSKg0PQjbdbAXepW91Kj00aq724yImTkjZOQeGtok9t+4TvtatVRO5lTBsMpO+v3vZIu4hPPUNYxgh/tWsvcuFkrJaF9KYKuMeQLD38L4GdyAaTpLmW4h7sPlPKKBpI0kiEqUcoOHovWPeb/WCyTGPHXtelXdOFi2qXUOAQKBgQDA1eFUb7y5zew4uoikdFZXsEOaOiK94AGQKKqWobssmE4SZnH2vj0qZ8sjIIDTrHb5v0I7DgziB8oOZTwqgpxdEPNymhDyUOvzWO02ECQjEvKJFrWLhVgExLJrfD+qrwDNg2k3ALxUIGazQxxZofIL/XFll5iC+K0rhjHodkBL6QKBgQCz/cT96dzA/u/EIedWk7lKGlIXTU6n3MCv3qunELKHrigph7Oq2YQXaMnMkNXIsJ6TO+aSkRJYy9vu91+wWaHlUYyppP/+Szpye3WykFzfgW494RVbwM+9nuX4zS8fXFXpRPvaYyXZXZmh3w1XbcaU2mEj/OrQWeqRs6xMKxQMwQKBgQCEUb6+m7WaoYyYGJcukY21ugOJTp8MPyuTafQ0TGINZa0cvyMwNrXt5S7kuabsmIlDMlQWBbwdVU72tcylNt5olfsXBlTekVx2rOyIjmbdkmDOZMDKLgOJshbj3mwpiNA6NRP7ZAvWhi0ykLhhYNigFZa8KVvDczQQi5KveoUAEQKBgQCoqvg9ETgjS0OYQkUtZoVOIdgnqfQ5ba7LoBivGACVEOjl8hq10dvsq+/fWSJdTNVtF8b8NiJBP9g72Wi8M2J5KLBuEQcrX4fH3FXxHUNK11ldd12ye76w7QSI4I52DusbC+APSLYhOYtMm18/PMnIl9RMCyhoUrp0CzE8t2m0gQKBgQCJ3uTKhcZP6fXbuqNLcYNx0EKekLbb8GPCEQyE3/wniT9O3ETwZxWwTYFdQb400AztGKMO2UIrbxKfiSQiCkNNnT6yzXLVIgRT16hP9VTUVYFoWEF7MTYFpPlLfsHH5moAIUZgAls5bP0MpXl77MX46C83YXEkT0h2jno/K/6qFQ==\n";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwcBlhNW8Z1SaQbsTvnmW4jNvPTVTu0b3aERFCoDiZUfcSxt7FQ9qA7EhnkngWDjntVBjHlCMEsDJ2thPBwV9eLyx874guu91bvrPkrZIfUMNNpRqDuBUwvgukeIURGLG51//1i+VZvwai4fup5JRq3CkeyaiSR7m74gI/rYKNeZhR9R3F+2WDljteuODiHvmuFe1GA6aseHu3+dlqmLeh6qMMkoCd8tTCXVW3domb3M9hUpdRm7K16Uqv4kwMBGCEX1gnJuVtmmIX/1kMIoOsRSlK3yf47sYE9fKBMEAl9Di8nL2j37GWx4u6r4vYQECxyZY/TIVbdKUHo9/2SScYQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:7770";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:7770";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
