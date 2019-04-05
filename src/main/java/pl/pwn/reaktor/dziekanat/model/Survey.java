package pl.pwn.reaktor.dziekanat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    private String mail;

    private String phone;

    private Boolean java;

    private Boolean python;

    private Boolean other;

    @Column(name = "other_desc")
    private String otherDesc;

    private String language;

    private String course;

    private int studentId;

    public Survey(String firstName, String lastName, String mail, String phone, Boolean java, Boolean python, Boolean other, String otherDesc, String language, String course, int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.java = java;
        this.python = python;
        this.other = other;
        this.otherDesc = otherDesc;
        this.language = language;
        this.course = course;
        this.studentId = studentId;
    }
}
