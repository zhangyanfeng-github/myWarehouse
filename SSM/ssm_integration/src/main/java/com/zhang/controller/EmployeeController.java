package com.zhang.controller;

import com.zhang.pojo.Employee;
import com.zhang.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("emp")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("select")
    public List<Employee> select() {
        List<Employee> all = employeeService.selectAll();
        log.info("测试成功 + {}", all);
        return all;
    }
}
