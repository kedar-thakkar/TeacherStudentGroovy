package com.ts.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class LoginResponse {
    private Long id;
    private String userName;
    private String email;
    private String contactNo;
    private String role;
    private String address;
    private String token
}
