package com.ecomshop.service.user;

import com.ecomshop.exception.ResourceNotFoundException;
import com.ecomshop.model.User;
import com.ecomshop.repository.UserRepository;
import com.ecomshop.request.CreateUserRequest;
import com.ecomshop.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {

        return userRepository.findById(userId).map(existing ->{
            existing.setFirstName(request.getFirstName());
            existing.setLastName(request.getLastName());
            return userRepository.save(existing);
        }).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () ->{
            throw new ResourceNotFoundException("User not Found!");
        });
    }
}
