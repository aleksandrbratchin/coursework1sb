package ru.bratchin.coursework1sb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bratchin.coursework1sb.model.dto.EmployeeDto;
import ru.bratchin.coursework1sb.model.dto.SalaryDto;
import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.model.mapper.api.MyMapper;
import ru.bratchin.coursework1sb.model.mapper.impl.employee.EmployeeMapper;
import ru.bratchin.coursework1sb.service.api.MyService;
import ru.bratchin.coursework1sb.service.impl.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final MyService<Employee, Integer> service;

    private final MyMapper<Employee, EmployeeDto> mapper;

    public EmployeeController(EmployeeService service, EmployeeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(
                    service.findAll().stream().map(mapper::toDto).toList(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка!");
        }
    }

    /***
     * Базовая сложность //todo
     */
    @GetMapping("/salarySum")
    public ResponseEntity<?> salarySum() {
        try {
            return new ResponseEntity<>(
                    new SalaryDto(service.salarySum()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при расчете трат за месяц!");
        }
    }

    @GetMapping("/averageSalary")
    public ResponseEntity<?> averageSalary() {
        try {
            return new ResponseEntity<>(
                    new SalaryDto(service.averageSalary()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при расчете cреднего значения зарплат!");
        }
    }

    @GetMapping("/minSalary")
    public ResponseEntity<?> minSalary() {
        try {
            return new ResponseEntity<>(
                    mapper.toDto(service.minSalary()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при поиске сотрудника с минимальной зарплатой!");
        }
    }

    @GetMapping("/maxSalary")
    public ResponseEntity<?> maxSalary() {
        try {
            return new ResponseEntity<>(
                    mapper.toDto(service.maxSalary()),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при поиске сотрудника с максимальной зарплатой!");
        }
    }

    /**
     * Средняя сложность //todo
     */
    @GetMapping("/filter/department/{department}")
    public ResponseEntity<?> findByDepartment(
            @PathVariable String department
    ) {
        try {
            return new ResponseEntity<>(
                    service.findByDepartment(department).stream().map(mapper::toDto).toList(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при поиске сотрудников в отделе " + department + "!");
        }
    }

    @GetMapping("/filter/salary/less/{salary}")
    public ResponseEntity<?> filterBySalaryLessThan(
            @PathVariable Double salary
    ) {
        try {
            return new ResponseEntity<>(
                    service.filterBySalaryLessThan(salary).stream().map(mapper::toDto).toList(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при поиске сотрудников c зарплатой менее " + salary + "!");
        }
    }

    @GetMapping("/filter/salary/more/{salary}")
    public ResponseEntity<?> filterBySalaryMoreThan(
            @PathVariable Double salary
    ) {
        try {
            return new ResponseEntity<>(
                    service.filterBySalaryMoreThan(salary).stream().map(mapper::toDto).toList(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при поиске сотрудников c зарплатой более " + salary + "!");
        }
    }

    @GetMapping("/salaryIndexing/{percent}")
    public ResponseEntity<?> salaryIndexing(
            @PathVariable Double percent
    ) {
        try {
            service.salaryIndexing(percent);
            return ResponseEntity.ok("Произведено индексирование зарплат на " + percent + "%!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при индексировании зарплат на " + percent + "%!");
        }
    }

    @GetMapping("/salaryIndexing/{percent}/department/{department}")
    public ResponseEntity<?> salaryIndexingByDepartment(
            @PathVariable Double percent,
            @PathVariable String department
    ) {
        try {
            service.salaryIndexingByDepartment(department, percent);
            return ResponseEntity.ok("Произведено индексирование зарплат в отделе " + department + " на " + percent + "%!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при индексировании зарплат в отделе " + department + " на " + percent + "%!");
        }
    }

    @GetMapping("/salarySum/department/{department}")
    public ResponseEntity<?> salarySumByDepartment(
            @PathVariable String department
    ) {
        try {
            return new ResponseEntity<>(
                    new SalaryDto(service.salarySumByDepartment(department)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Ошибка при расчете трат за месяц в отделе " + department + "!");
        }
    }

    /**
     * Тестирование высокая сложность //todo
     */
    @PostMapping
    public ResponseEntity<?> add(
            @RequestBody EmployeeDto employeeDto
    ) {
        try {
            service.add(mapper.fromDto(employeeDto));
            return ResponseEntity.ok("Данные добавлены!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при добавлении!");
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(
            @RequestBody EmployeeDto employeeDto
    ) {
        try {
            service.update(mapper.fromDto(employeeDto));
            return ResponseEntity.ok("Данные изменены!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при изменении!!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable("id") Integer id
    ) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Работник с ID = " + id + " успешно удален!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при удалении!");
        }
    }

}
