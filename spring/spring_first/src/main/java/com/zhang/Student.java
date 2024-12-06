package com.zhang;

import java.util.Map;

public class Student {

    private String studentId;
    private String studentName;

    //一个学生对应多个老师
    private Map<String, Teacher> teacherMap;


    public void run() {
        System.out.println("学号：" + studentId + "，姓名：" + studentName);
        System.out.println(teacherMap);
    }

    public Map<String, Teacher> getTeacherMap() {
        return teacherMap;
    }

    public void setTeacherMap(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", teacherMap=" + teacherMap +
                '}';
    }
}
