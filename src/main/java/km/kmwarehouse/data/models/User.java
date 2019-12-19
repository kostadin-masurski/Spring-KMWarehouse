package km.kmwarehouse.data.models;

import km.kmwarehouse.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @ManyToMany
    private Set<Role> roles;

    @Column
    private boolean isAccountNonExpired;

    @Column
    private boolean isAccountNonLocked;

    @Column
    private boolean isCredentialsNonExpired;

    @Column
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;

    public User() {
        authorities = new HashSet<>();
    }
}
