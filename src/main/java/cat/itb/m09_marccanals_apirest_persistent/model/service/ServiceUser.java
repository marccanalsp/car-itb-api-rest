package cat.itb.m09_marccanals_apirest_persistent.model.service;

import cat.itb.m09_marccanals_apirest_persistent.model.entity.User;
import cat.itb.m09_marccanals_apirest_persistent.model.repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final RepositoryUser repositoryUser;
    private final PasswordEncoder encoder;

    public User checkByUsername(String username){
        return repositoryUser.findByUsername(username).orElse(null);
    }
    public User createUser(User newUser){
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        repositoryUser.save(newUser);
        return newUser;
    }
    public List<User> llistUsers(){
        return repositoryUser.findAll();
    }
}
