package com.ts.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String userName;
    private String email;
    private String password;
    private String contactNo;
    private String uploadProfile;
    private String address;
    private String role;
    private Date createdOn;
    private Date modifiedOn;
    private Boolean isActive;
    private Boolean isVerified;
}
