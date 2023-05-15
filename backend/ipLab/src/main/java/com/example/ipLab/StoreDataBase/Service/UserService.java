package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Configurations.jwt.JwtException;
import com.example.ipLab.StoreDataBase.Configurations.jwt.JwtsProvider;
import com.example.ipLab.StoreDataBase.DTO.UserDTO;
import com.example.ipLab.StoreDataBase.Exceptions.CustomerNotFoundException;
import com.example.ipLab.StoreDataBase.Exceptions.UserNotFoundException;
import com.example.ipLab.StoreDataBase.Model.CustomUser;
import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.User;
import com.example.ipLab.StoreDataBase.Model.UserRole;
import com.example.ipLab.StoreDataBase.Repositories.UserRepository;
import com.example.ipLab.StoreDataBase.util.validation.ValidationException;
import com.example.ipLab.StoreDataBase.util.validation.ValidatorUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidatorUtil validatorUtil;
    private final JwtsProvider jwtProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ValidatorUtil validatorUtil,
                       JwtsProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validatorUtil = validatorUtil;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public User addUser(String login, String password, String passwordConfirm, UserRole role, Long userId){
        if (getUserByLogin(login) != null){
            throw new ValidationException(String.format("User with login %s already exists", login));
        }
        if (!Objects.equals(password, passwordConfirm)) {
            throw new ValidationException("Passwords not equals");
        }
        final User user = new User(login, passwordEncoder.encode(password), role, userId);
        validatorUtil.validate(user);
        return userRepository.save(user);
    }

    @Transactional
    public User getUserByLogin(String login){
        return  userRepository.findOneByLogin(login);
    }

    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        if (!jwtProvider.validateToken(token)) {
            throw new JwtException("Bad token");
        }
        final String userLogin = jwtProvider.getLogin(token);
        if (userLogin.isEmpty()) {
            throw new JwtException("Token is not contain Login");
        }
        return loadUserByUsername(userLogin);
    }

    public String loginAndGetToken(UserDTO userDto) {
        final User user = getUserByLogin(userDto.getLogin());
        if (user == null) {
            throw new UserNotFoundException(userDto.getLogin());
        }
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new UserNotFoundException(user.getLogin());
        }
        return jwtProvider.generateToken(user.getLogin(), user.getRole().name());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User userEntity = getUserByLogin(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUser(
                userEntity.getLogin(), userEntity.getPassword(), Collections.singleton(userEntity.getRole()), userEntity.getUserId(), userEntity.getRole());
    }
}
