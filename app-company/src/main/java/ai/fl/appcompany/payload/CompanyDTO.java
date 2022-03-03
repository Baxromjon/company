package ai.fl.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private String name;
    private String address;
    private String leaderFullName;
    private String email;
    private String webSite;
    private String phoneNumber;

    @NotNull
    @Valid
    private UserDTO userDTO;
}
