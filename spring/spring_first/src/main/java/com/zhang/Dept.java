package com.zhang;

import java.util.List;

//部门类
public class Dept {

    //一个部门有多个员工
    private List<Emp> empList;
    private String dname;

    public List<Emp> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }

    public void info() {
        System.out.println(dname + "部门");
        for (Emp emp : empList) {
            System.out.println(emp.getEname());
        }
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
