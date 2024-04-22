package br.com.fiap.healthix.repository;
import br.com.fiap.healthix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
 
   
}
