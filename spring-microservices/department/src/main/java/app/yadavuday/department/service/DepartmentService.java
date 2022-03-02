package app.yadavuday.department.service;

import app.yadavuday.department.controller.DepartmentController;
import app.yadavuday.department.model.Department;
import app.yadavuday.department.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public Department saveDepartmentService(Department department) {
        log.info("inside saveDepartmentService method");
        try {
            return departmentRepository.save(department);
        } catch (Exception e) {
            log.info("unable to save department " + department.toString());
            return null;
        }
    }

    public Department getDepartmentByIdService(Long deptId) {
        log.info("inside getDepartmentByIdService method");
        Optional<Department> departmentOp = departmentRepository.findById(deptId);
        if (departmentOp.isEmpty()) {
            log.info("department not found with id : " + deptId);
            return null;
        }
        return departmentOp.get();
    }
}
