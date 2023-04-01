package com.edamame.service;

import com.edamame.model.Role;
import com.edamame.model.User;
import com.edamame.repository.RoleRepo;
import com.edamame.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImplementation implements UserService{

    private UserRepo userRepository;
    private RoleRepo roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepo userRepository,
                                     RoleRepo roleRepository,
                                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }

        user.setRoles(Arrays.asList(role));;
        userRepository.save(user);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
