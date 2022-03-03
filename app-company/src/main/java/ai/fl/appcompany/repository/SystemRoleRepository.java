package ai.fl.appcompany.repository;

import ai.fl.appcompany.entity.SystemRole;
import ai.fl.appcompany.entity.enums.SystemRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemRoleRepository extends JpaRepository<SystemRole, UUID> {
    SystemRole findBySystemRoleEnum(SystemRoleEnum roleUser);
}
