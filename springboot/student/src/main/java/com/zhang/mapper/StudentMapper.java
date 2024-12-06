package com.zhang.mapper;

import com.zhang.pojo.Student;

import java.util.List;

public interface StudentMapper {

    int insertStudent();

    int delete(int id);

    int updateStudent();

    List<Student> queryAll();

}
