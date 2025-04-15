package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.PlannedExpense;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.PlannedExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannedExpenseService {

    @Autowired
    public PlannedExpenseRepository repo;

    //region get service
    public List<PlannedExpense> list ()
    {
        return repo.findAll();
    }

    public PlannedExpense findById(long id)
    {
        return repo.getReferenceById(id);
    }
    //endregion

    //region post service
    public void create (PlannedExpense input)
    {
        repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,PlannedExpense  input)
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
