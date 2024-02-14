package com.example.springfox.model;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique identifier for the student")
    Integer id;
    @ApiModelProperty(value = "Name of the student", example = "John Doe")
    String name;
    @ApiModelProperty(value = "Age of the student", example = "25")
    Integer age;
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}




