package ai.fl.appcompany.controller;

import ai.fl.appcompany.entity.User;
import ai.fl.appcompany.payload.ApiResponse;
import ai.fl.appcompany.payload.UserDTO;
import ai.fl.appcompany.security.CurrentUser;
import ai.fl.appcompany.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/me")
    public HttpEntity<?> getUserMe(@CurrentUser User user) {
        return ResponseEntity.ok(new ApiResponse("success",true, user));
    }
    @PreAuthorize("hasAnyRole('ROLE_COMPANY')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAllUser(@CurrentUser User user) {
        return ResponseEntity.ok(userService.getAllUsers(user));
    }

    @PreAuthorize("hasAnyRole('ROLE_COMPANY')")
    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_COMPANY')")
    @PostMapping("/add")
    public HttpEntity<?> save(@Valid @RequestBody UserDTO userDTO, @CurrentUser User user) {
        ApiResponse add = userService.add(userDTO, user);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }

    @PreAuthorize("hasAnyRole('ROLE_COMPANY')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO, @CurrentUser User user) {
        ApiResponse edit = userService.edit(id, userDTO, user);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(edit);
    }

    @PreAuthorize("hasAnyRole('ROLE_COMPANY')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse delete = userService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(delete);
    }
}
