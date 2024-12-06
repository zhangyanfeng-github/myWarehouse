package com.zhang.service.impl;

import com.zhang.mapper.StudentMapper;
import com.zhang.pojo.Student;
import com.zhang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public int insertStudent() {
        return studentMapper.insertStudent();
    }

    @Transactional
    public int delete(int id) {
        return studentMapper.delete(id);
    }

    @Transactional
    public int updateStudent() {
        return studentMapper.updateStudent();
    }

    public List<Student> queryAll() {
        return studentMapper.queryAll();
    }

}
