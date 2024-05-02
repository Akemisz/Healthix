package br.com.fiap.healthix.repository;
import br.com.fiap.healthix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
 
    Page<User> findByUserNomeIgnoreCase(String user, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.user.nome = :user")
    Page<User> findByUserNome(@Param("user") String user, Pageable pageable);
   
}
