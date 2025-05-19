package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.Home;
import com.example.HomeFinances.Repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeService {

    @Autowired
    public HomeRepository repo;

    //region get service
    public List<Home> list ()
    {
        return repo.findAll();
    }

    public Optional<Home> findById(long id)
    {
        return repo.findById(id);
    }
    //endregion

    //region post service
    public Home create (Home input)
    {
        return repo.save(input);
    }
    //endregion

    //region put service
    public void update (long id,Home  input)
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
