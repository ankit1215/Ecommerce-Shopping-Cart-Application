package com.ecomshop.service.user;

import com.ecomshop.dto.UserDto;
import com.ecomshop.exception.AlreadyExitsException;
import com.ecomshop.exception.ResourceNotFoundException;
import com.ecomshop.model.User;
import com.ecomshop.repository.UserRepository;
import com.ecomshop.request.CreateUserRequest;
import com.ecomshop.request.UpdateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req ->{
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepository.save(user);
                }).orElseThrow(()-> new AlreadyExitsException("Oops! " + request.getEmail() + " already exists!"));
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

    @Override
    public UserDto convertUserToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
