package com.smago.booking.entity;

import com.smago.booking.common.Role;
import com.smago.booking.dto.RegDataDto;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Data
@Entity
@Table(name = "users", schema = "auth")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    @Override
    @Nonnull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var data = this.authorities.stream()
                .map(authority -> authority.getRole().name())
                .toList();
        return AuthorityUtils.createAuthorityList(data);
    }

    public UserEntity(String email, String username, String password, List<Authority> authorities) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.authorities = authorities;
        authorities.forEach(it -> it.setUser(this));
    }

    public static UserEntity toUser(RegDataDto dto, String password, Role... roles) {
        if (roles.length == 0) {
            throw new IllegalArgumentException("Необходимо указать роль пользователя");
        }
        List<Authority> authorities = Stream.of(roles)
                .map(Authority::new)
                .toList();
        return new UserEntity(dto.email(), dto.username(), password, authorities);
    }
}
