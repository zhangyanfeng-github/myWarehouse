package com.zhang.mapper;

import com.zhang.pojo.Email;

public interface EmailMapper {
    Integer addEmail(Email email);

    Object getEmailBySaveName(String saveName);
}
