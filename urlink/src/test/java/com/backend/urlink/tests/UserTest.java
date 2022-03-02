package com.backend.urlink.tests;

import com.backend.urlink.models.User;
import com.backend.urlink.repository.CollectionRepository;
import com.backend.urlink.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
public class UserTest {

    private final UserRepository userRepository;

    @Autowired
    public UserTest(UserRepository userRepository, CollectionRepository collectionRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void saveUser() {

        var user = User.builder().userEmail("ironman@avengers.com").userName("Iron Man").build();
        userRepository.save(user);

        var user2 = User.builder().userEmail("camamerica@avengers.com").userName("Captain America").build();
        userRepository.save(user2);
    }

    @Test
    void getUser() {
        var savedUser = userRepository.findById(1L);
        System.out.println(savedUser);
    }

    @Test
    public void deleteUser() {
        var user = User.builder().userEmail("hawkeye@avengers.com").userName("Hawk Eye").build();
        var savedUser = userRepository.save(user);
        userRepository.deleteById(savedUser.getUserId());
    }


    @Test
    @Transactional
    public void getUserWithCollection() {
        Optional<User> userOp = userRepository.findById(1L);

        if (userOp.isPresent()) {
            var user = userOp.get();
            System.out.println(user.getCollections());
        }
    }
}
