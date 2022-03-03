package ai.fl.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
}
