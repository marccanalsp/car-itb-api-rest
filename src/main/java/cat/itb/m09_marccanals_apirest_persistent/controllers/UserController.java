package cat.itb.m09_marccanals_apirest_persistent.controllers;

import cat.itb.m09_marccanals_apirest_persistent.model.entity.User;
import cat.itb.m09_marccanals_apirest_persistent.model.entity.UserCheckDTO;
import cat.itb.m09_marccanals_apirest_persistent.model.service.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final ServiceUser serviceUser;

    @GetMapping("/me")
    public UserCheckDTO userCheckDTO(@AuthenticationPrincipal User user){
        return new UserCheckDTO(user.getUsername(), user.getAvatar(), user.getRol());
    }
    /*
    {
    "username":"Marc",
    "password":"hola",
    "avatar":"https://i0.wp.com/hipertextual.com/wp-content/uploads/2021/05/dogecoin-elon-musk-tesla-13.jpeg?resize=800%2C525&quality=50&strip=all&ssl=1"
    }
    Afegeix id automàticament
     */
    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        try {
            User res = serviceUser.createUser(newUser);
            UserCheckDTO usu = new UserCheckDTO(res.getUsername(), res.getAvatar(), res.getRol());
            return new ResponseEntity<UserCheckDTO>(usu, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            //per si intentem afegir 2 usuaris amb el mateix username, saltarà excepció
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> llistarUsuarisDTO() {
        List<User> res = serviceUser.llistUsers();
        List<UserCheckDTO> aux = new ArrayList();
        for (User user : res) {
            aux.add(new UserCheckDTO(user.getUsername(), user.getAvatar(), user.getRol()));
        }
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(aux);
    }
}
