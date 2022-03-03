package ai.fl.appcompany.component;

import ai.fl.appcompany.entity.SystemRole;
import ai.fl.appcompany.entity.User;
import ai.fl.appcompany.entity.enums.SystemRoleEnum;
import ai.fl.appcompany.repository.SystemRoleRepository;
import ai.fl.appcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SystemRoleRepository systemRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Value(value = "${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {

            SystemRoleEnum[] systemRoleEnums = SystemRoleEnum.values();
            List<SystemRole> systemRoles = new ArrayList<>();
            for (SystemRoleEnum roleEnum : systemRoleEnums) {
                systemRoles.add(new SystemRole(roleEnum));
            }
            systemRoleRepository.saveAll(systemRoles);

            User admin = new User(
                    "SuperAdmin",
                    "SuperAdmin",
                    "SuperAdmin",
                    "AA1234567",
                    "+998990068005",
                    passwordEncoder.encode("root123"),
                    Collections.singletonList(systemRoleRepository.findBySystemRoleEnum(SystemRoleEnum.ROLE_ADMIN))
            );
            userRepository.save(admin);


        }
    }
}
