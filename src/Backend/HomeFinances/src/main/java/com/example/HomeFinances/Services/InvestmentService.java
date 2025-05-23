package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.Investment;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    @Autowired
    public InvestmentRepository repo;

    //region get service
    public List<Investment> list ()
    {
        return repo.findAll();
    }

    public Investment findById(long id)
    {
        return repo.getReferenceById(id);
    }

    public List<Investment> findByHomeId(long id) {
        return repo.FindByHomeId(id);
    }
    //endregion

    //region post service
    public void create (Investment input)
    {
        repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,Investment  input)
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
