package com.zhang.mybatis.pojo;

public class Dept {
    private Integer did;
    private String dept_name;

    public Dept(Integer did, String dept_name) {
        this.did = did;
        this.dept_name = dept_name;
    }



    public Dept() {
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
