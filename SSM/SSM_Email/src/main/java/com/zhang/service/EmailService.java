package com.zhang.service;

import com.zhang.pojo.Email;

public interface EmailService {
    Integer addEmail(Email email);

    String getEmailBySaveName(String saveName);
}
