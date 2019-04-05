package pl.pwn.reaktor.dziekanat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Embeddable
//jesli bysmy chcieli zebu klasa address była używana w kilku tabelach
public class Address {

    private String street;

    private String city;
}
