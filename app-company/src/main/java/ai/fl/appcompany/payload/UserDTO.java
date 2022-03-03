package ai.fl.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String middleName;
    @NotNull
    @Pattern(regexp = "^(?!^0+$)[A-Z]{2}[0-9]{7}$")
    private String passportSerialAndNumber;
    @NotNull
    private String phoneNumber;
    private String password;
    @NotNull
    private String position;
    private String company;

    public UserDTO(String firstName, String lastName, String middleName, String passportSerialAndNumber, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passportSerialAndNumber = passportSerialAndNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public UserDTO(String firstName, String lastName, String middleName, String passportSerialAndNumber, String phoneNumber, String position, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passportSerialAndNumber = passportSerialAndNumber;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.company = company;
    }
}
