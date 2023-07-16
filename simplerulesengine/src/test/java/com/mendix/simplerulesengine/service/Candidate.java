package com.mendix.simplerulesengine.service;

public class Candidate {
    private String name;
    private boolean isPassed;
    private int percentage;
    private int examAttempt;
    private boolean isGraceMarkGiven;

    public String getName() {
        return name;
    }

    public Candidate(String name, boolean isPassed, int percentage, int examAttempt) {
        this.name = name;
        this.isPassed = isPassed;
        this.percentage = percentage;
        this.examAttempt = examAttempt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getExamAttempt() {
        return examAttempt;
    }

    public void setExamAttempt(int examAttempt) {
        this.examAttempt = examAttempt;
    }

    public boolean isGraceMarkGiven() {
        return isGraceMarkGiven;
    }

    public void setGraceMarkGiven(boolean graceMarkGiven) {
        isGraceMarkGiven = graceMarkGiven;
    }
}
