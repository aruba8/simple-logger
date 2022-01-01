package ca.erik.service.impl;

import ca.erik.model.User;
import ca.erik.repository.UserRepository;
import ca.erik.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.insert(user);
    }

    @Override
    public User findOne(Long tgId) {
        return userRepository.findFirstByTgId(tgId);
    }

    @Override
    public List<User> find() {
        return userRepository.findAll();
    }

    @Override
    public User findOrCreate(User user) {
        User userInDb = userRepository.findFirstByTgId(user.getTgId());
        if (userInDb == null) {
            user.setCreated(new Date());
            userInDb = create(user);
        }
        return userInDb;
    }
}
