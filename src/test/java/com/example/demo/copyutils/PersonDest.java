package com.example.demo.copyutils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chong.xie
 * @Date 2023/8/9 14:08
 */
public class PersonDest {
    private Integer id;
    private String username;
    private Integer age;

    private Integer phone;

    private Student student;

    private Map<String, String> otherMap = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Map<String, String> getOtherMap() {
        return otherMap;
    }

    public void setOtherMap(Map<String, String> otherMap) {
        this.otherMap = otherMap;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "PersonDest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", student=" + student.toString() +
                ", otherMap=" + otherMap +
                '}';
    }
}
