package com.example.HomeFinances.Controllers;

import com.example.HomeFinances.Models.ActualExpense;
import com.example.HomeFinances.Models.ExpenseMatching;
import com.example.HomeFinances.Services.ActualExpenseService;
import com.example.HomeFinances.Services.ExpenseMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/expensematching")
public class ExpenseMatchingController {
    @Autowired
    public ExpenseMatchingService service;

    //region get controller
    @GetMapping("/")
    public ResponseEntity<?> list()
    {
        try {
            List<ExpenseMatching> response = service.list();
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
            ExpenseMatching response = service.findById(id);
            if (response == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el registro con el id " + id);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error en el servidor: " + e.getMessage());
        }
    }
    //endregion

    //region post controller

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ExpenseMatching input){
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

    //endregion

    //region put controller

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ExpenseMatching input)
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
