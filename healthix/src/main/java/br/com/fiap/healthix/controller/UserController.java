package br.com.fiap.healthix.controller;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //Buscar usuario por nome ou, retornar pagina inteira 
    @GetMapping
    public Page<User> index(
        @RequestParam(required = false) String user,
        @PageableDefault(sort = "data", direction = Direction.DESC)Pageable pageable
    ){
        
        if(user != null){
            return userRepository.findByUserNomeIgnoreCase(user, pageable);
        }

        return userRepository.findAll(pageable);

    }

    @PostMapping
    @ResponseStatus(CREATED)
    public User create(@RequestBody @Valid  User user){
        log.info("Criando usuário: {}", user);
        return  userRepository.save(user);
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

        user.setId(id); 
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
