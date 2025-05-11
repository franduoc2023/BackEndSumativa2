package com.sumativa2.Mapper;

import com.sumativa2.Dto.UsuariosDto;
import com.sumativa2.Model.Usuarios;

public class UsuariosMapper {

     public static UsuariosDto toDto(Usuarios usuario) {
 
        return new UsuariosDto.Builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .password(usuario.getPassword())
                .nombreUsuario(usuario.getNombreUsuario())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .email(usuario.getEmail())
                .build();
    }

     public static Usuarios toEntity(UsuariosDto dto) {
 
        return new Usuarios.Builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .password(dto.getPassword())
                .nombreUsuario(dto.getNombreUsuario())
                .fechaNacimiento(dto.getFechaNacimiento())
                .email(dto.getEmail())
                .build();
    }
}