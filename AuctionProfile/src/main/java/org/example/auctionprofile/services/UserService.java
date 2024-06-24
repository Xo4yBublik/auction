package org.example.auctionprofile.services;

import org.example.auctionprofile.dtos.UserRegisterDto;
import org.example.auctionprofile.entities.UserEntity;
import org.example.auctionprofile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(UserRegisterDto registerDto) {
        userRepository.saveUser(registerDto);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserEntity getUser(Long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }

    public void updateUser(UserRegisterDto updatedUser) {
        userRepository.updateUserById(updatedUser);
    }

    public Long getCurrentUserId() {
        return userRepository.getCurrentUserId();
    }
}