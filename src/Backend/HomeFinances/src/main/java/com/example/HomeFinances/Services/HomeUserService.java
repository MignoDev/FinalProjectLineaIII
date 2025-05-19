package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.HomeUser;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.HomeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeUserService {

    @Autowired
    public HomeUserRepository repo;

    //region get service
    public List<HomeUser> list ()
    {
        return repo.findAll();
    }

    public HomeUser findById(long id)
    {
        return repo.getReferenceById(id);
    }

    public HomeUser findByUserId(long userId) {
        return repo.findByUserId(userId);
    }
    //endregion

    //region post service
    public void create (HomeUser input)
    {
        repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,HomeUser  input)
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
