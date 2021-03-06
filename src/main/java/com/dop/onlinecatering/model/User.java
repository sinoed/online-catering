package com.dop.onlinecatering.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditModel implements UserDetails {

    @NotBlank
    @NotNull
    @Column(unique = true)
    @Size(min = 8, max = 20)
    private String phoneNumber;         //this is the field which should be unique and identifies the user

    @Column(unique = true)
    @Size(min = 8, max = 20)
    private String username;            //or we can switch to user name
                                        //todo Decide Which one to implement

    @Column(length = 100)
    private String password;

    private String firstName;
    private String lastName;
    private char gender = 'u';
    private String email;
    private String address;
    private String postalCode;
    private String nationalCode;

    @ManyToOne
    private City city;

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}