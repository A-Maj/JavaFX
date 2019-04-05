package pl.pwn.reaktor.dziekanat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private boolean active;

    @OneToOne
    @JoinColumn(name = "nasz_student", nullable = true)
    private Student student;
}
