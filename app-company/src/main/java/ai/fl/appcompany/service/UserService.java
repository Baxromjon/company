package ai.fl.appcompany.service;

import ai.fl.appcompany.entity.Company;
import ai.fl.appcompany.entity.User;
import ai.fl.appcompany.payload.ApiResponse;
import ai.fl.appcompany.payload.UserDTO;
import ai.fl.appcompany.projection.UserProjection;
import ai.fl.appcompany.repository.CompanyRepository;
import ai.fl.appcompany.repository.SystemRoleRepository;
import ai.fl.appcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SystemRoleRepository systemRoleRepository;

//    public ApiResponse getAll() {
//        try {
//            return new ApiResponse("Successfully", userRepository.finAllByRole());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ApiResponse("Error", false);
//    }

    public ApiResponse getById(UUID id) {
        return new ApiResponse(true, userRepository.findById(id));
    }

    public ApiResponse add(UserDTO userDTO, User user) {
        boolean b = userRepository.existsByPhoneNumber(userDTO.getPhoneNumber());
        if (b)
            return new ApiResponse("This phone number allReady exists", false);
        User user1 = new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getMiddleName(),
                userDTO.getPassportSerialAndNumber(),
                userDTO.getPosition(),
                userDTO.getPhoneNumber(),
                passwordEncoder.encode("root123"),
                user.getCompany()
        );
        userRepository.save(user1);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse edit(UUID id, UserDTO userDTO, User user) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("User not found by given Id", false);
        User user1 = byId.get();
        user1.setFirstName(userDTO.getFirstName());
        user1.setLastName(userDTO.getLastName());
        user1.setMiddleName(userDTO.getMiddleName());
        user1.setPhoneNumber(userDTO.getPhoneNumber());
        user1.setPassportSerialAndNumber(userDTO.getPassportSerialAndNumber());
//        user1.setPassword(userDTO.getPassword());
        user1.setPosition(userDTO.getPosition());
        userRepository.save(user1);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse delete(UUID id) {
        try {
            //bu yerda deleteById() methodini ishlatishim mumkin edi, lekin ma`lumot qimmatli narsa, shuning uchun
            //faqat enable ni false qilib qo`ydim.
            //userRepository.deleteById(id);
            Optional<User> byId = userRepository.findById(id);
            User user = byId.get();
            user.setEnabled(false);
            userRepository.save(user);
            return new ApiResponse("Successfully deleted", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error in delete", false);
    }

    public List<UserProjection> getAllUsers(User user) {
        return userRepository.findByUserId(user.getCompany().getId());
    }
}
