package ai.fl.appcompany.controller;

import ai.fl.appcompany.payload.ApiResponse;
import ai.fl.appcompany.payload.CompanyDTO;
import ai.fl.appcompany.service.CompanyService;
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
@RequestMapping("/api/company")
@CrossOrigin
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/allCompany")
    public HttpEntity<?> getAllCompany() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/getById/{id}")
    public HttpEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(companyService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public HttpEntity<?> saveCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        ApiResponse save = companyService.save(companyDTO);
        return ResponseEntity.status(save.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(save);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id, @RequestBody CompanyDTO companyDTO) {
        ApiResponse edit = companyService.edit(id, companyDTO);
        return ResponseEntity.status(edit.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(edit);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        ApiResponse delete = companyService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(delete);
    }

}
