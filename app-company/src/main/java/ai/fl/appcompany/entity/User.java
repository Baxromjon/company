package ai.fl.appcompany.entity;

import ai.fl.appcompany.entity.template.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleName;
    @Column(nullable = false, unique = true)
    private String passportSerialAndNumber;
    private String position;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Company company;

    @ManyToMany
    @JoinTable(name = "users_systems_roles", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "system_role_id")})
    private List<SystemRole> systemRoles;

    private boolean enabled = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.systemRoles;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public User(String firstName, String lastName, String middleName, String passportSerialAndNumber, String position, String phoneNumber, String password, Company company, List<SystemRole> systemRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passportSerialAndNumber = passportSerialAndNumber;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.company = company;
        this.systemRoles = systemRoles;
    }

    public User(String firstName, String lastName, String middleName, String passportSerialAndNumber, String phoneNumber, String password, List<SystemRole> systemRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passportSerialAndNumber=passportSerialAndNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.systemRoles = systemRoles;
    }

    public User(String firstName, String lastName, String middleName, String passportSerialAndNumber, String position, String phoneNumber, String password, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passportSerialAndNumber = passportSerialAndNumber;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.company = company;
    }

    public User(String firstName, String lastName, String middleName, String phoneNumber, String password, Company company, List<SystemRole> systemRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.company = company;
        this.systemRoles = systemRoles;
    }

    public User(String firstName, String lastName, String middleName, String passportSerialAndNumber, String phoneNumber, String password, Company company, List<SystemRole> systemRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passportSerialAndNumber = passportSerialAndNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.company = company;
        this.systemRoles = systemRoles;
    }
}
