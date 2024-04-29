package com.example.banco.usuario;

import com.example.banco.util.Pessoa;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "USUARIO")
public class Usuario extends Pessoa implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String password;
    private String roles;
    private String email;
    private String telefone;
    private boolean active;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var roleList = this.roles.split(",");
        return Arrays.stream(roleList).map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

}
