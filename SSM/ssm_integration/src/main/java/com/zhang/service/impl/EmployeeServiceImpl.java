package com.zhang.service.impl;

import com.zhang.mapper.EmployeeMapper;
import com.zhang.pojo.Employee;
import com.zhang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> selectAll() {
        List<Employee> employees = employeeMapper.selectAll();
        return employees;
    }
}
