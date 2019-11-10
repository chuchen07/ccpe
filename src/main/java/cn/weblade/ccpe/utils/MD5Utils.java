package cn.weblade.ccpe.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Utils {
    private static String hashAlgorithmName = "MD5";//加密方式
    private static int hashIterations = 1024;//加密1024次

    /**
     * @param crdentials 加密原，也就是原密码值
     * @param salt       盐值，这里取邮箱
     * @return
     */
    public static String encript_password(String crdentials, String salt) {

        Object encriptPassword = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        return String.valueOf(encriptPassword);
    }
    //这个是生成数据库数据的方式。直接运行main就OK
    public static void main() {
        System.out.println(encript_password("123456", "993643197@qq.com"));
    }
}