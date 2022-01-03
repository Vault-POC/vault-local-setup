package com.persistent.vaultlocalsetup.service;

import com.persistent.vaultlocalsetup.execption.UserNotFoundException;
import com.persistent.vaultlocalsetup.model.User;
import com.persistent.vaultlocalsetup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("authService")
@Transactional
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository; }
    
    public User getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is with id:" + id + " was not found!"));
        return user;
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

}
