package app.udayyadav.user.controller;

import app.udayyadav.user.entity.Users;
import app.udayyadav.user.service.UserService;
import app.udayyadav.user.vo.ResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public Users saveUserController(@RequestBody Users users) {
        log.info("inside saveUserController");
        return userService.saveUserService(users);
    }

    @GetMapping("/{userId}")
    public ResponseTemplate getUserController(@PathVariable("userId") Long userId) {
        log.info("inside getUserController");
        return userService.getUserByIdService(userId);
    }
}
