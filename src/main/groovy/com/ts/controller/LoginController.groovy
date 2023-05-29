package com.ts.controller

import com.ts.constant.AppConstants
import com.ts.dto.LoginRequest
import com.ts.dto.LoginResponse
import com.ts.dto.ResponseDTO
import com.ts.entity.UserEntity
import com.ts.exception.InvalidCredException
import com.ts.exception.UserNotVerifiedException
import com.ts.repository.UserRepository
import com.ts.security.JwtUtil
import com.ts.serviceimpl.UserDetailServiceImpl
import jakarta.annotation.Resource
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "customUserService")
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticate;

    @PostMapping("/authenticate")
    ResponseDTO login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            logger.info("inside authenticate....");
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(loginRequest.getEmail());
            final String token = jwtTokenUtil.generateTokenFromUsername(userDetails.getUsername());
            Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(loginRequest.getEmail());
            if (userEntity.isPresent()) {
                UserEntity userData = userEntity.get();
                if (userData.isVerified == Boolean.FALSE) {
                    throw new UserNotVerifiedException(AppConstants.USER_NOT_VERIFIED + userData.email)
                }
                LoginResponse loginResponse = new LoginResponse();
                BeanUtils.copyProperties(userData, loginResponse);
                loginResponse.setProperty("token", token);
                return new ResponseDTO("200", "Login Success!", loginResponse);
            } else {
                throw new RuntimeException("User not found")
            }
        } catch (Exception e) {
            logger.error("error occurred while login....");
            throw new InvalidCredException("Invalid Credentials");
        }
    }

  /*  private void authenticate(String name, String password) throws Exception {
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);

        try {
            authenticate.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception(AppConstants.LOGIN_INVALID_CREDENTIALS, e);
        }
    }*/
}
