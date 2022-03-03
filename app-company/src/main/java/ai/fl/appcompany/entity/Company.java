package ai.fl.appcompany.entity;

import ai.fl.appcompany.entity.template.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * created by Baxromjon
 * 28.02.2022
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private User user;
    private String leaderFullName;

    @Column(nullable = false)
    private String address;

    private String email;

    private String webSite;

    @Column(nullable = false)
    private String phoneNumber;

    private boolean enable;

    public Company(String name, String leaderFullName, String address, String email, String webSite, String phoneNumber) {
        this.name = name;
        this.leaderFullName = leaderFullName;
        this.address = address;
        this.email = email;
        this.webSite = webSite;
        this.phoneNumber = phoneNumber;
    }
}
