package com.ts.serviceimpl

import com.ts.entity.UserEntity
import com.ts.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("customUserService")
@Slf4j
class UserDetailServiceImpl implements UserDetailsService {
    public static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("inside loadUserByUsername method");
        Optional<UserEntity> entity = userRepository.findOneByEmailIgnoreCase(username);
        UserEntity userEntity = null;
        if (entity.isPresent()) {
            userEntity = entity.get();
        }
        if (userEntity == null) {
            throw new UsernameNotFoundException("", new Throwable("Invalid Creds"));
        }
        UserDetails user = User.withUsername(userEntity.getEmail()).password(userEntity.getPassword())
                .roles("Teacher", "Student").build();
        return user;
    }
}
