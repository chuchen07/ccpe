package cn.weblade.ccpe.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String email;
    private String userName;
    private String passWord;
    private String userState;
    private String userCode;

}
