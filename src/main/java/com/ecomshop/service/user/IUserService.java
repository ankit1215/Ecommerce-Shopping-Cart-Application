package com.ecomshop.service.user;

import com.ecomshop.dto.UserDto;
import com.ecomshop.model.User;
import com.ecomshop.request.CreateUserRequest;
import com.ecomshop.request.UpdateUserRequest;

public interface IUserService {

    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, Long userId);

    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
