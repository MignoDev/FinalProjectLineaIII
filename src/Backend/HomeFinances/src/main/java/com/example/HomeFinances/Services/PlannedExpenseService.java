package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.PlannedExpense;
import com.example.HomeFinances.Models.PlannedExpenseDetail;
import com.example.HomeFinances.Models.PlannedExpenseWithDetailDTO;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.PlannedExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<PlannedExpense> findByHomeId(long id) {
        return repo.findByHomeId(id);
    }

    public List<PlannedExpenseWithDetailDTO> findAllExpenses(long id)
    {
        List<Object[]> results = repo.findFullExpenses(id);
        List<PlannedExpenseWithDetailDTO> dtos = new ArrayList<>();

        for (Object[] row : results)
        {
            PlannedExpense pe = new PlannedExpense();
            pe.setId(((Long) row[0]));
            pe.setAmount(((Double) row[1]));
            pe.setComment(((String) row[2]));
            pe.setDescription(((String) row[3]));
            pe.setHomeId(((Long) row[4]));
            pe.setTypeId(((Long) row[5]));

            PlannedExpenseDetail ped = new PlannedExpenseDetail();
            ped.setId(((Long) row[6]));
            ped.setDate(((java.sql.Date) row[7]).toLocalDate());
            ped.setPlannedExpenseId(((Long) row[8]));

            dtos.add(new PlannedExpenseWithDetailDTO(pe, ped));
        }

        return dtos;
    }
    //endregion

    //region post service
    public PlannedExpense create (PlannedExpense input)
    {
        return repo.save(input);
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
