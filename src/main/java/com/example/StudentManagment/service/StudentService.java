package com.example.StudentManagment.service;

import com.example.StudentManagment.entity.Student;
import com.example.StudentManagment.repo.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class StudentService {
@Autowired StudentRepository studentRepository;

public Student saveStudent(Student student){
    Student newStudent = studentRepository.save(student);
    return newStudent;
}

public List<Student> getAllStudent(){
    return studentRepository.findAll();
}

public Student getStudentById(Long id){
    return studentRepository.findById(id).get();
}

    public boolean deleteStudentById(Long id) {
        Student stu = studentRepository.findById(id).get();
        if(stu!=null){
            studentRepository.delete(stu);
            return true;
        }
        return false;
    }

    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");

    }

}

