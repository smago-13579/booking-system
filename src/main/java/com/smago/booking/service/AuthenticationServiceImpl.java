package com.smago.booking.service;

import com.smago.booking.common.Role;
import com.smago.booking.dto.RegDataDto;
import com.smago.booking.entity.UserEntity;
import com.smago.booking.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(RegDataDto dto) {
        try {
            log.info("Registration for user: {}", dto.username());

            UserEntity user = UserEntity.toUser(dto, passwordEncoder.encode(dto.password()), Role.CUSTOMER);
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            throw e;
        }
    }
}
