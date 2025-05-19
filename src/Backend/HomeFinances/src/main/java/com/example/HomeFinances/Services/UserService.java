package com.example.HomeFinances.Services;

import com.example.HomeFinances.Models.EarnInvestment;
import com.example.HomeFinances.Models.LoginRequest;
import com.example.HomeFinances.Models.User;
import com.example.HomeFinances.Repositories.EarnInvestmentRepository;
import com.example.HomeFinances.Repositories.UserRepository;
import com.example.HomeFinances.Utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository repo;



    //region get service
    public List<User> list ()
    {
        return repo.findAll();
    }

    public Optional<User> findById(long id)
    {
        return repo.findById(id);
    }

    public User findByUserName(String user) {
        Optional<User> userOpt = repo.findByUserName(user);
        if (userOpt.isPresent())
        {
            return userOpt.get();
        }
        return null;
    }
    //endregion

    //region post service
    public void create (User input)
    {
        String hashedPassword = PasswordUtils.hashPassword(input.getPassword());
        User user = new User();
        user.setUserName(input.getUserName());
        user.setPassword(hashedPassword);
        user.setNickName(input.getNickName());
        repo.save(user);
    }
    //endregion

    //region put service
    public void update (long id,User  input)
    {
        if (!repo.existsById(id))
        {
            throw new RuntimeException("Registro con el id " + id + " No existe");
        }
        String hashedPassword = PasswordUtils.hashPassword(input.getPassword());
        User user = new User();
        user.setUserName(input.getUserName());
        user.setPassword(hashedPassword);
        user.setNickName(input.getNickName());
        repo.save(user);
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

    public boolean login (LoginRequest input)
    {
        boolean authorization = false;
        Optional<User> userOpt = repo.findByUserName(input.getUserName());
        if (userOpt.isPresent())
        {
            authorization = PasswordUtils.verifyPassword(input.getPassword(), userOpt.get().getPassword());
        }
        return authorization;
    }
    //endregion
}
