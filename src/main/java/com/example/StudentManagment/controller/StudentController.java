package com.example.StudentManagment.controller;

import com.example.StudentManagment.entity.Student;
import com.example.StudentManagment.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;


    @GetMapping("/")
    public String index(Model model){
        List<Student> stuList = studentService.getAllStudent();
        model.addAttribute("studentList",stuList);
        return "index";
    }

    @GetMapping("/saveStudent")
    public String saveStudent(){
        return "saveStudent";
    }

    @GetMapping("/editDetails/{id}")
    public String editStudentDetails(@PathVariable Long id, Model model){
        Student stu = studentService.getStudentById(id);
        model.addAttribute("stu",stu);
        return "editDetails";
    }

    @PostMapping("/addStudent")
        public String addStudent(@ModelAttribute Student stu, HttpSession session){
        Student newStu = studentService.saveStudent(stu);
        if (newStu!=null){
            session.setAttribute("msg","submitted successfully");
        }
        else{
            session.setAttribute("msg","something wrong");
        }
        return "redirect:/saveStudent";
        }
    @PostMapping("/updateStudentDtls")
    public String updateStudent(@ModelAttribute Student stu, HttpSession session) {
        // System.out.println(stu);

        Student updateStu = studentService.saveStudent(stu);

        if (updateStu != null) {
            // System.out.println("save success");
            session.setAttribute("msg", "Update sucessfully");
        } else {
            // System.out.println("something wrong ");
            session.setAttribute("msg", "something wrong ");
        }
        return "redirect:/";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        boolean present = studentService.deleteStudentById(id);
        if (present) {
            session.setAttribute("msg", "Delete sucessfully");
        } else {
            session.setAttribute("msg", "something wrong on server");
        }
        return "redirect:/";
    }
    }

