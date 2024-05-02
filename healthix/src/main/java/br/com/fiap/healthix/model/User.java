package br.com.fiap.healthix.model;

import jakarta.persistence.GeneratedValue;
import br.com.fiap.healthix.validation.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Entity
@Builder 
@NoArgsConstructor
@AllArgsConstructor
 
public class User {
   
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "{user.nome.notblank}")
    private  String nome;

    @NotBlank(message = "{user.email.notblank}")
    @Email
    private String email;

    @NotBlank(message = "{user.senha.notblank}")
    private String senha;

}
