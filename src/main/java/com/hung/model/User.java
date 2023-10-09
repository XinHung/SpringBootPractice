package com.hung.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.hung.ch6.validator.FieldMatch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "密碼和確認密碼必須相等")
public class User {
    private Integer id;
    @NotBlank(message="使用者名稱不能為空")
    @Length(min=4, max=12, message="使用者名稱長度必須在 4 到 12 字元之間")
    private String userName;
    @NotBlank(message="密碼不能為空")
    @Length(min=4, max=8, message="密碼長度必須在 4 到 8 字元之間")
    private String password;
    private String confirmPassword;
    private Boolean sex;
    @NotBlank(message="E-mail不能為空")
    @Email(message="不是有效的 E-mail 地址")
    private String email;
    private String pwdQuestion;
    private String pwdAnswer;
    private Date regDate;
    private Date lastLoginDate;
    private String registerIp;
    private String lastLoginIp;
}

