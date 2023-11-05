package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

@Entity
public class Job extends AbstractEntity {

    //test pass when commented out, bootrun only works if uncommented
    @Id
    @GeneratedValue
//    private int id;
//
//    private String name;

    @ManyToOne
    private Employer employer;
    private String skills;

    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer employer, String someSkills) {
        this.employer = employer;
        this.skills = someSkills;
    }

    // Getters and setters.

//    public int getID(){
//        return id;
//    }

    public Employer getEmployer(){
        return employer;
    }

    public void setEmployer(Employer employer){
        this.employer = employer;
    }


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
