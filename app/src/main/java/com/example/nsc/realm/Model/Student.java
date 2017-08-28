package com.example.nsc.realm.Model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by NSC on 8/21/2017.
 */

public class Student extends RealmObject {

    @Required
    String studentName;

    int studentScore;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(int studentScore) {
        this.studentScore = studentScore;
    }

    public Student(String studentName, int studentScore) {
        this.studentName = studentName;
        this.studentScore = studentScore;
    }

    public Student() {

    }
}
