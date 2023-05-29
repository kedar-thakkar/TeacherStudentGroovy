package com.ts.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UserCreateDTO {
    private String userName;
    private String email;
    private String password;
    private String contactNo;
    private String uploadProfile;
    private String role;
    private String address;
}
