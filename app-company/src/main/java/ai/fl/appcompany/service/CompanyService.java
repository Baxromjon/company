package ai.fl.appcompany.service;

import ai.fl.appcompany.entity.Company;
import ai.fl.appcompany.entity.User;
import ai.fl.appcompany.entity.enums.SystemRoleEnum;
import ai.fl.appcompany.payload.ApiResponse;
import ai.fl.appcompany.payload.CompanyDTO;
import ai.fl.appcompany.repository.CompanyRepository;
import ai.fl.appcompany.repository.SystemRoleRepository;
import ai.fl.appcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    SystemRoleRepository systemRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public ApiResponse getAll() {
        return new ApiResponse(true, companyRepository.getAllCompany());
    }

    public ApiResponse getById(UUID id) {
        try {
            return new ApiResponse(true, companyRepository.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(false, "error");
    }


    @Transactional(noRollbackFor = {NullPointerException.class})
    public ApiResponse save(CompanyDTO companyDTO) {
        Boolean exists = companyRepository.existsByNameAndPhoneNumber(companyDTO.getName(), companyDTO.getPhoneNumber());
        if (exists) {
            return new ApiResponse(false, "This Company allReady added");
        }

        Company company = new Company(
                companyDTO.getName(),
                companyDTO.getUserDTO()+companyDTO.getUserDTO().getLastName(),
                companyDTO.getAddress(),
                companyDTO.getEmail(),
                companyDTO.getWebSite(),
                companyDTO.getPhoneNumber(),
                true
        );
        companyRepository.save(company);

        User user=new User(
                companyDTO.getUserDTO().getFirstName(),
                companyDTO.getUserDTO().getLastName(),
                companyDTO.getUserDTO().getMiddleName(),
                companyDTO.getUserDTO().getPassportSerialAndNumber(),
                "Korxona Raxbari",
                companyDTO.getUserDTO().getPhoneNumber(),
                passwordEncoder.encode(companyDTO.getUserDTO().getPassword()),
                company,
                Collections.singletonList(systemRoleRepository.findBySystemRoleEnum(SystemRoleEnum.ROLE_COMPANY))
        );

        userRepository.save(user);
        return new ApiResponse(true, "Successfully added");
    }

    public ApiResponse edit(UUID id, CompanyDTO companyDTO) {
        Optional<Company> optional = companyRepository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResponse(false, "Company not found by given Id");
        }
        Company company = optional.get();
        company.setName(companyDTO.getName());
        company.setLeaderFullName(companyDTO.getLeaderFullName());
        company.setAddress(companyDTO.getAddress());
        company.setEmail(companyDTO.getEmail());
        company.setWebSite(companyDTO.getWebSite());
        company.setPhoneNumber(companyDTO.getPhoneNumber());

        companyRepository.save(company);
        return new ApiResponse(true, "Successfully edited");
    }

    public ApiResponse delete(UUID id) {
        try {
            Optional<Company> byId = companyRepository.findById(id);
            Company company=byId.get();
            company.setEnable(false);
            companyRepository.save(company);
            return new ApiResponse(true, "Successfully deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(false, "Error in delete");
    }
}
