package app.udayyadav.user.service;

import app.udayyadav.user.entity.Users;
import app.udayyadav.user.repository.UserRepository;
import app.udayyadav.user.vo.Department;
import app.udayyadav.user.vo.ResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public Users saveUserService(Users users) {
        log.info("inside saveUserService");
        try {
            return userRepository.save(users);
        } catch (Exception e) {
            log.info("user not found");
            return null;
        }
    }

    public ResponseTemplate getUserByIdService(Long userId) {
        log.info("inside getUserByIdService");
        Optional<Users> userOp = userRepository.findById(userId);
        if (userOp.isEmpty()) {
            log.info("user not found");
            return null;
        }

        var user = userOp.get();

        var department = restTemplate
                .getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);

        var responseVo = new ResponseTemplate();
        responseVo.setUsers(user);
        responseVo.setDepartment(department);

        return responseVo;
    }
}
