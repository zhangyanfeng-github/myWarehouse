package com.zhang.controller;

import com.zhang.pojo.Student;
import com.zhang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("delete")
    public int delete() {
        return studentService.delete(3);
    }

    @GetMapping("insert")
    public int insertStudent(){
        return studentService.insertStudent();
    }

    @GetMapping("update")
    public int updateStudent(){
        return studentService.updateStudent();
    }

    @GetMapping("selectAll")
    public List<Student> queryAll() {
        return studentService.queryAll();
    }

}
