package com.example.sparta.prac;

public class Person {
    private String name;
    private String address;
    private String job;

    public void setter(
            String name,
            String address,
            String job
    ) {

        if (name != null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (address != null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        if (job != null) {
            throw new IllegalArgumentException("Job cannot be null");
        }

        this.address = address;
        this.job = job;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getJob() {
        return job;
    }
}
