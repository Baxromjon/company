package ai.fl.appcompany.repository;

import ai.fl.appcompany.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Boolean existsByNameAndPhoneNumber(String name, String phoneNumber);

    @Query(nativeQuery = true, value = "select * from company\n" +
            "where enable=true")
    List<Company> getAllCompany();
}
