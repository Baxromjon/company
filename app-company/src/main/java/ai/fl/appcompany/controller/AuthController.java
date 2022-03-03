package ai.fl.appcompany.controller;

import ai.fl.appcompany.entity.User;
import ai.fl.appcompany.payload.ApiResponse;
import ai.fl.appcompany.payload.LoginDTO;
import ai.fl.appcompany.security.JwtTokenProvider;
import ai.fl.appcompany.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public HttpEntity<?> checkLogin(@Valid @RequestBody LoginDTO loginDTO){
        try {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getPhoneNumber(),
                    loginDTO.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user= (User) authentication.getPrincipal();
            String token= jwtTokenProvider.generateToken(user.getId());
            return ResponseEntity.status(200).body(new ApiResponse("Successfully", true, token));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(409).body(new ApiResponse("Error", false));
    }
}
