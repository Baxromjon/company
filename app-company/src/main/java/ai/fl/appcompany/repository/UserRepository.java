package ai.fl.appcompany.repository;

import ai.fl.appcompany.entity.SystemRole;
import ai.fl.appcompany.entity.User;
import ai.fl.appcompany.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query(nativeQuery = true, value = "select cast(u.id as varchar)        as id,\n" +
            "       u.first_name                 as firstName,\n" +
            "       u.last_name                  as lastName,\n" +
            "       u.middle_name                as middleName,\n" +
            "       u.phone_number               as phoneNumber,\n" +
            "       u.passport_serial_and_number as passportSerialAndNumber,\n" +
            "       u.position                   as position\n" +
            "from users u\n" +
            "where u.company_id = :companyId\n" +
            "and u.enabled=true")
    List<UserProjection> findByUserId(UUID companyId);
}
