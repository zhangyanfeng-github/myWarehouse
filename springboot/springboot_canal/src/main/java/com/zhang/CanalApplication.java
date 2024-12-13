package com.zhang;

import com.zhang.listener.CanalEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Author : 张彦锋
 * Date : 2024/12/7  20:32
 * Text :
 */
@SpringBootApplication
@EnableCaching
public class CanalApplication implements CommandLineRunner {

    @Autowired
    private CanalEventListener canalEventListener;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        canalEventListener.run();
    }
}