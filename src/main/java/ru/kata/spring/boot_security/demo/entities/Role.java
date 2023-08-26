package ru.kata.spring.boot_security.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }



    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(Integer id, String name, Set<User> user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}