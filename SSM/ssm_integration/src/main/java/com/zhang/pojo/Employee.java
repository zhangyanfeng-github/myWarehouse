package com.zhang.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;

}
