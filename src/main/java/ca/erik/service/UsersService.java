package ca.erik.service;

import ca.erik.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    User create(User user);

    User findOne(Long tgId);

    List<User> find();

    User findOrCreate(User user);
}
