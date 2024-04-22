package br.com.fiap.healthix.controller;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.fiap.healthix.model.User;
import br.com.fiap.healthix.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> index(){
        return  userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public User create(@RequestBody @Valid  User user){
        log.info("Criando usuário: {}", user);
        return  userRepository.save(user);
    }
    

    @GetMapping("{id}")
    public ResponseEntity<User> get (@PathVariable Long id) {
        log.info("Buscar por id:{}", id);

        return userRepository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Deletando Usuario {}", id);

        verificarSeExisteUser(id);
        userRepository.deleteById(id);
    }



    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        log.info("Atualizando nome usuário id {} para {}", id, user);

        verificarSeExisteUser(id);

        user.setNome(user.getNome()); 
        return userRepository.save(user);
        
    }

    private void verificarSeExisteUser(Long id){
        userRepository
        .findById(id)
        .orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
        );

    }


}
