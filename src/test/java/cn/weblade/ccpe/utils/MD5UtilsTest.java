package cn.weblade.ccpe.utils;

import org.junit.jupiter.api.Test;

import static cn.weblade.ccpe.utils.MD5Utils.encript_password;
import static org.junit.jupiter.api.Assertions.*;

class MD5UtilsTest {

    @Test
    void main() {
        System.out.println(encript_password("123456", "993643191@qq.com"));
    }
}