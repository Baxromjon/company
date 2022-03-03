package ai.fl.appcompany.entity;

import ai.fl.appcompany.entity.enums.SystemRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SystemRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SystemRoleEnum systemRoleEnum;

    public SystemRole(SystemRoleEnum systemRoleEnum) {
        this.systemRoleEnum = systemRoleEnum;
    }

    @Override
    public String getAuthority() {
        return systemRoleEnum.name();
    }
}
