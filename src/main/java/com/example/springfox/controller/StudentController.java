package com.example.springfox.controller;
import com.example.springfox.model.Student;
import com.example.springfox.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Api(value = "Student Management System", description = "Operations pertaining to student management")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    @ApiOperation(value = "Get all students", response = List.class)
    private List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/new")
    @ApiOperation(value = "Create a new student", response = ResponseEntity.class)
    private ResponseEntity<String> createStudent(@ApiParam(value = "Student object to be created", required = true) @RequestBody Student student) {
        try {
            studentService.saveOrUpdate(student);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("New Student created with id: " + student.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/ageGreaterThan/{age}")
    @ApiOperation(value = "Get students older than a certain age", response = List.class)
    private List<Student> getStudentsByAgeGreaterThan(@ApiParam(value = "Minimum age to filter students", required = true) @PathVariable Integer age) {
        return studentService.getStudentsByAgeGreaterThan(age);
    }

    @GetMapping("/nameContaining/{name}")
    @ApiOperation(value = "Get students with a name containing a certain string", response = List.class)
    private List<Student> getStudentsByNameContaining( @ApiParam(value = "String to search for in student names", required = true) @PathVariable String name) {
        return studentService.getStudentsByNameContaining(name);
    }


    @DeleteMapping("/deleteByName/{name}")
    @ApiOperation(value = "Delete student by name", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student deleted successfully"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    private ResponseEntity<String> deleteStudentByName(@ApiParam(value = "Name of the student to be deleted", required = true) @PathVariable String name) {
        studentService.deleteStudentByName(name);
        return new ResponseEntity<>("Student with name " + name + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/countByAgeGreaterThan/{age}")
    @ApiOperation(value = "Count students older than a certain age", response = Long.class)
    private long countStudentsByAgeGreaterThan(@ApiParam(value = "Minimum age to count students", required = true) @PathVariable Integer age) {
        return studentService.countStudentsByAgeGreaterThan(age);
    }

//    @PutMapping("/updateAge/{id}/{age}")
//    @ApiOperation(value = "Update student's age", response = ResponseEntity.class)
//    private ResponseEntity<String> updateStudentAge(@ApiParam(value = "Student ID", required = true) @PathVariable Integer id,
//                                                    @ApiParam(value = "New age of the student", required = true) @PathVariable Integer age) {
//        try {
//            Student student = studentService.getStudentById(id);
//            if (student != null) {
//                student.setAge(age);
//                studentService.saveOrUpdate(student);
//                return new ResponseEntity<>("Student with ID " + id + " age updated successfully", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Student with ID " + id + " not found", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception exception) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
