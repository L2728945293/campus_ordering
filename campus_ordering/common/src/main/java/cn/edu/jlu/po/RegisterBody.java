package cn.edu.jlu.po;


import lombok.Data;

@Data
public class RegisterBody {
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String avatar;


}
