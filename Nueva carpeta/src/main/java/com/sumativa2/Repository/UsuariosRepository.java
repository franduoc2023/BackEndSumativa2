package com.sumativa2.Repository;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa2.Model.Usuarios;

  
public interface UsuariosRepository extends JpaRepository<Usuarios , Long>{
    Optional<Usuarios>findByEmail(String email);
      
} 
