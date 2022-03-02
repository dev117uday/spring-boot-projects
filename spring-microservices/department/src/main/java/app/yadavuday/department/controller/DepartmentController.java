package app.yadavuday.department.controller;

import app.yadavuday.department.model.Department;
import app.yadavuday.department.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department saveDepartment(@RequestBody Department department) {
        log.info("inside saveDepartment method");
        return departmentService.saveDepartmentService(department);
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentController(@PathVariable("departmentId") Long deptId) {
        log.info("inside getDepartmentController method");
        return departmentService.getDepartmentByIdService(deptId);
    }
}
