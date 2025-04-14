package com.sumativa2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa2.Model.Foro;
import com.sumativa2.Model.Usuarios;

 

public interface ForoRepository extends JpaRepository<Foro, Long> {
    List<Foro> findByUsuario(Usuarios usuario);
}
