package com.school.studentstatusmonitoringsystem.dto;

public class ExamSummaryDTO {

    private String courseName;

    private double test;
    private double mid;
    private double fin;
    private double total;

    public ExamSummaryDTO(String courseName,
                          double test,
                          double mid,
                          double fin,
                          double total) {
        this.courseName = courseName;
        this.test = test;
        this.mid = mid;
        this.fin = fin;
        this.total = total;
    }

    // getters

    public String getCourseName() { return courseName; }
    public double getTest() { return test; }
    public double getMid() { return mid; }
    public double getFin() { return fin; }
    public double getTotal() { return total; }
}