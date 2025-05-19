package com.example.HomeFinances.Controllers;

import com.example.HomeFinances.Models.ActualExpense;
import com.example.HomeFinances.Models.LoginRequest;
import com.example.HomeFinances.Models.User;
import com.example.HomeFinances.Services.ActualExpenseService;
import com.example.HomeFinances.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public UserService service;

    //region get controller
    @GetMapping("/")
    public ResponseEntity<?> list()
    {
        try {
            List<User> response = service.list();
            return ResponseEntity.ok(response);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable long id)
    {
        try {
            Optional<User> response = service.findById(id);
            if (response.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el registro con el id " + id);
            }
            return ResponseEntity.ok(response.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> findUserName(@PathVariable String userName)
    {
        try {
            User response = service.findByUserName(userName);
            if (response == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el registro con el nombre " + userName);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }
    }
    //endregion

    //region post controller

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody User input){
        try {
            service.create(input);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest input)
    {
        try {
            boolean response = service.login(input);
            return ResponseEntity.ok(response);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }
    }

    //endregion

    //region put controller

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody User input)
    {
        try {
            service.update(id, input);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }
    }

    //endregion

    //region delete controller

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id)
    {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }
    }

    //endregion
}
