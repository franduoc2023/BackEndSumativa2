package com.sumativa2.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumativa2.Dto.ForoDto;
import com.sumativa2.Dto.UsuariosDto;
import com.sumativa2.Mapper.ForoMapper;
import com.sumativa2.Mapper.UsuariosMapper;
import com.sumativa2.Model.Foro;
import com.sumativa2.Model.Usuarios;
import com.sumativa2.Repository.ForoRepository;
import com.sumativa2.Repository.UsuariosRepository;

@Service
public class UsuariosImpl implements UsuariosService {

    @Autowired 
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ForoRepository foroRepository;

    @Override
    public UsuariosDto createUsuarios(UsuariosDto dto) {
        Usuarios nuevoUsuario = UsuariosMapper.toEntity(dto);
        Usuarios guardado = usuariosRepository.save(nuevoUsuario);
        return UsuariosMapper.toDto(guardado);
    }

    @Override
    public List<UsuariosDto> getAllUsuarios() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return usuarios.stream()
                       .map(UsuariosMapper::toDto)
                       .toList();
    }


    public Usuarios getUser(Long id) {
        return usuariosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public UsuariosDto updateUsario(Long id, UsuariosDto dto) {
        Usuarios existente = usuariosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        Usuarios actualizado = new Usuarios.Builder()
            .id(existente.getId())  
            .nombre(dto.getNombre())
            .apellido(dto.getApellido())
            .nombreUsuario(dto.getNombreUsuario())
            .password(dto.getPassword())
            .fechaNacimiento(dto.getFechaNacimiento())
            .email(dto.getEmail())
            .build();
    
        Usuarios guardado = usuariosRepository.save(actualizado);
        return UsuariosMapper.toDto(guardado);
    }
    

    @Override
    public void deleteUsuario(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
        }
    }

    @Override
    public Usuarios login(String email, String password) {
        return usuariosRepository.findByEmail(email)
                                 .filter(u -> u.getPassword().equals(password))
                                 .orElse(null);
    }

    @Override
    public List<ForoDto> getForosPorUsuario(Usuarios usuario) {
        List<Foro> foros = foroRepository.findByUsuario(usuario);
        return foros.stream()
                    .map(ForoMapper::toDto)
                    .toList();
    }

    @Override
    public ForoDto comentarioForo(ForoDto foroDto, Usuarios usuario) {
        Foro foro = foroRepository.findById(foroDto.getId())
            .orElseThrow(() -> new RuntimeException("Foro no encontrado"));

        foro.setComentario(foroDto.getComentario());
        foro.setFechaCreacion(LocalDate.now());
        foro.setUsuario(usuario);  

        Foro guardado = foroRepository.save(foro);
        return ForoMapper.toDto(guardado);
    }
}
