package com.zhang.service;

import com.zhang.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    public int insertStudent();


    public int delete(int id);


    public int updateStudent();

    public List<Student> queryAll();

}
