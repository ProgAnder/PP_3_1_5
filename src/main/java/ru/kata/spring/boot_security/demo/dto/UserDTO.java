package ru.kata.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.Setter;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Set;


@Getter
@Setter
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Byte age;

    private Set<Role> roles;


    public UserDTO() {
    }

    public UserDTO(int id, String username, String password, String firstName, String lastName, Byte age, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
    }

    public UserDTO(String username, String password, String firstName, String lastName, Byte age, Set<Role> roles) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }
}
