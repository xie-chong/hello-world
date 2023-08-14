package com.example.demo.copyutils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chong.xie
 * @Date 2023/8/9 14:07
 */
public class PersonSource implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Student student;

    private Map<String, String> otherMap = new HashMap<>();

    public PersonSource(Integer id, String username, String password, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public PersonSource() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        return "PersonSource{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", student=" + student.toString() +
                ", otherMap=" + otherMap +
                '}';
    }
}
