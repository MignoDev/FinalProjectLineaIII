package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.ActualExpense;
import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarnInvestmentService {

    @Autowired
    public EarnInvestmentRepository repo;

    //region get service
    public List<EarnInvestment> list ()
    {
        return repo.findAll();
    }

    public EarnInvestment findById(long id)
    {
        return repo.getReferenceById(id);
    }
    //endregion

    //region post service
    public void create (EarnInvestment input)
    {
        repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,EarnInvestment  input)
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
