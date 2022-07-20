package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
private final StudentRepository studentRepository;
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentRepository.findAll();
//                List.of(
//                new Student(
//                        1L,
//                        "Jigmee Wangchuk Sherpa",
//                        "jigmee@gmail.com",
//                        LocalDate.of(2000, Month.APRIL,2),
//                        12
//
//                )
//        ) ;
    }

    public void addNewStudent(Student student) {
    Optional<Student> studentOptional= studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()){
        throw new IllegalStateException("email taken");
    }
       studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist= studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("Student with id "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);
    }

@Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
    Student student=studentRepository.findById(studentId).
            orElseThrow(()->new IllegalStateException(
                    "Student with id "+studentId+" does not exist"));

    if (name != null
            &&name.length()>0&&
            !Objects.equals(student.getName(),name)){
        student.setName(name);
    }
    if (email!=null&&email.length()>0&&!Objects.equals(student.getEmail(),email)){
Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
if (studentOptional.isPresent()){
    throw new IllegalStateException("email taken");

}
student.setEmail(email);
    }
    }
}
