package cn.weblade.ccpe.utils;

import java.util.UUID;

public class UUIDUtils {
    //生成的uuid会有-号间隔，所以得去除掉
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
