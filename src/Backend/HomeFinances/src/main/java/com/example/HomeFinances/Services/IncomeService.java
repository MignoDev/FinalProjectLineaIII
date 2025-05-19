package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.Income;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    @Autowired
    public IncomeRepository repo;

    //region get service
    public List<Income> list ()
    {
        return repo.findAll();
    }

    public Income findById(long id)
    {
        return repo.getReferenceById(id);
    }

    public List<Income> findByHomeId(long id) {
        return repo.findByHomeId(id);
    }
    //endregion

    //region post service
    public void create (Income input)
    {
        repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,Income  input)
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
