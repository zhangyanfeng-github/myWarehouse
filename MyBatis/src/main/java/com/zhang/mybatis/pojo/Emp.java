package com.zhang.mybatis.pojo;

public class Emp {
    private Integer eid;
    private String emp_name;
    private Integer age;
    private String sex;
    private String email;
    private Integer did;

    public Emp(Integer eid, String emp_name, Integer age, String sex, String email, Integer did) {
        this.eid = eid;
        this.emp_name = emp_name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.did = did;
    }

    public Emp() {
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "eid=" + eid +
                ", emp_name='" + emp_name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", did=" + did +
                '}';
    }
}
