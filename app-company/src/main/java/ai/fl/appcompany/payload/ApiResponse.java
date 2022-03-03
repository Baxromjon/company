package ai.fl.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private Object object;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public ApiResponse(boolean success, Object object) {
        this.success = success;
        this.object = object;
    }
}
