package com.zhang.service;

import com.zhang.pojo.Email;

/**
 * Author : 张彦锋
 * Date : 2024/11/27  17:25
 * Text :
 */
public interface EmailService {
    /**
     * Send email object.
     *
     * @param email the email
     * @return the object
     */
    Object sendEmail(Email email);
}
