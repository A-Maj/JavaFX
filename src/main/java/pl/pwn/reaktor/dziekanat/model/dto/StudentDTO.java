package pl.pwn.reaktor.dziekanat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pwn.reaktor.dziekanat.model.RoleEnum;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private int id;

    private String login;

    private String firstName;

    private String lastName;

    private String street;

    private String city;
}
