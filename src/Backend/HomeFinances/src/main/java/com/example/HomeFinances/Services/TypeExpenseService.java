package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.TypeExpense;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.TypeExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeExpenseService {

    @Autowired
    public TypeExpenseRepository repo;

    //region get service
    public List<TypeExpense> list ()
    {
        return repo.findAll();
    }

    public TypeExpense findById(long id)
    {
        return repo.getReferenceById(id);
    }
    //endregion

    //region post service
    public void create (TypeExpense input)
    {
        repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,TypeExpense  input)
    {
        if (!repo.existsById(id))
        {
            throw new RuntimeException("Registro con el id " + id + " No existe");
        }
        repo.save(input);
    }
    //endregion

    //region delete service
    public void deleteById(long id)
    {
        if(!repo.existsById(id))
        {
            throw new RuntimeException("Registro con el id " + id + " No existe");
        }
        repo.deleteById(id);
    }
    //endregion
}
