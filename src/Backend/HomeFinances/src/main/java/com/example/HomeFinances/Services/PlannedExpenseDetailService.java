package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.PlannedExpenseDetail;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.PlannedExpenseDetailRepository;
import com.example.HomeFinances.Repositories.PlannedExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannedExpenseDetailService {

    @Autowired
    public PlannedExpenseDetailRepository repo;

    //region get service
    public List<PlannedExpenseDetail> list ()
    {
        return repo.findAll();
    }

    public PlannedExpenseDetail findById(long id)
    {
        return repo.getReferenceById(id);
    }
    //endregion

    //region post service
    public void create (PlannedExpenseDetail input)
    {
        repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,PlannedExpenseDetail  input)
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
