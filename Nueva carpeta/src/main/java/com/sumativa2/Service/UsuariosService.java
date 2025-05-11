package com.sumativa2.Service;
import java.util.List;

import com.sumativa2.Dto.ForoDto;
import com.sumativa2.Dto.UsuariosDto;
import com.sumativa2.Model.Usuarios;
 
public interface UsuariosService {

    UsuariosDto createUsuarios(UsuariosDto usuariodto);

    List<UsuariosDto> getAllUsuarios();
    UsuariosDto updateUsario(Long id, UsuariosDto dto);
    void deleteUsuario(Long id);
    Usuarios login(String email, String password);
     List<ForoDto> getForosPorUsuario(Usuarios usuario);
     ForoDto comentarioForo(ForoDto foroDto, Usuarios usuario );
     Usuarios getUser (Long id);
 }  