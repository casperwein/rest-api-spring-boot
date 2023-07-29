package net.marro.springboot.restApiApplication.controller;

import net.marro.springboot.restApiApplication.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api-v1")
public class StudentController {

    @GetMapping("student/resentity")
    public ResponseEntity<Student> getStudentsRE(){
        Student student = new Student(
                1,
                "carles",
                "darwin"
        );
//        return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-headers", "testing-headers")
                .body(student);
    }

    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
             1,
             "carles",
             "darwin"
        );
        return student;
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "william", "yunior"));
        students.add(new Student(2, "muhammad", "ikor"));
        students.add(new Student(3, "marsel", "radityo"));
        students.add(new Student(4, "putri", "sekar"));
        return students;
    }

    @GetMapping("students/resentity-list")
    public ResponseEntity<List<Student>> getStudentREList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "william", "yunior"));
        students.add(new Student(2, "muhammad", "ikor"));
        students.add(new Student(3, "marsel", "radityo"));
        students.add(new Student(4, "putri", "sekar"));
        return ResponseEntity.ok()
                .header("custom-header", "custom-header")
                .body(students);
    }

    //    spring boot rest api with path variable
    @GetMapping("students/{id}")
        public Student studentPathVariable(@PathVariable("id") int studentId) {
            return new Student(studentId, "William", "Yunior");
    }

    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariableMore(@PathVariable("id") int studentId,
                                           @PathVariable("first-name") String firstName,
                                           @PathVariable("last-name") String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    // spring boot rest api with request params
    // http://localhost:8080/students/query?id=10
    @GetMapping("students/query")
    public Student studentWithParams(@RequestParam int id) {
        return new Student(id, "dimas", "anggara");
    }

    // http://localhost:8080/students/api/query?id=10&firstName=Andi&lastName=diki
    @GetMapping("students/api/query")
    public Student studentMultipleParams(@RequestParam int id,
                                         @RequestParam String firstName,
                                         @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    // spring boot rest api that handle post
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public  Student createStudents(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // spring boot rest api handles http put request
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // spring boot rest api handles http delete
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "Student deleted succesfully!";
    }
}
