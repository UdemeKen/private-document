package com.udemekendrick.privatedocument.service.impl;

import com.udemekendrick.privatedocument.entity.ConfirmationEntity;
import com.udemekendrick.privatedocument.entity.CredentialEntity;
import com.udemekendrick.privatedocument.entity.RoleEntity;
import com.udemekendrick.privatedocument.entity.UserEntity;
import com.udemekendrick.privatedocument.enumeration.Authority;
import com.udemekendrick.privatedocument.enumeration.EventType;
import com.udemekendrick.privatedocument.event.UserEvent;
import com.udemekendrick.privatedocument.exception.ApiException;
import com.udemekendrick.privatedocument.repository.ConfirmationRepository;
import com.udemekendrick.privatedocument.repository.CredentialRepository;
import com.udemekendrick.privatedocument.repository.RoleRepository;
import com.udemekendrick.privatedocument.repository.UserRepository;
import com.udemekendrick.privatedocument.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.udemekendrick.privatedocument.utils.UserUtils.createUserEntity;

/**
 * Author: Udeme Kendrick
 *
 * @version 1.0
 * @license MIT License
 * @see <a href="mailto:udemekendrick@gmail.com">udemekendrick@gmail.com</a>
 * @see <a href="https://udemekendrick.vercel.app">https://udemekendrick.vercel.app</a>
 * @since 10/23/2025
 */
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final ConfirmationRepository confirmationRepository;
//    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepository.save(createNewUser(firstName, lastName, email));
        var credentialEntity = new CredentialEntity(userEntity, password);
        credentialRepository.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }

    @Override
    public RoleEntity getRoleName(String name) {
        var role = roleRepository.findByNameIgnoreCase(name);
        return role.orElseThrow(()-> new ApiException("Role not found"));
    }

    @Override
    public void verifyAccountKey(String key) {
        var confirmationEntity = getUserConfirmation(key);
        var userEntity = getUserEntityByEmail(confirmationEntity.getUserEntity().getEmail());
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        confirmationRepository.delete(confirmationEntity);
    }

    private UserEntity getUserEntityByEmail(String email) {
        var userByEmail = userRepository.findByEmailIgnoreCase(email);
        return userByEmail.orElseThrow(() -> new ApiException("User not found"));
    }

    private ConfirmationEntity getUserConfirmation(String key) {
        return confirmationRepository.findByKey(key).orElseThrow(() -> new ApiException("Confirmation key not found"));
    }

    public UserEntity createNewUser(String firstName,String lastName,String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName,lastName, email, role);
    }
}
