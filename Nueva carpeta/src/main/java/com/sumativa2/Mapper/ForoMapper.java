package com.sumativa2.Mapper;

import com.sumativa2.Dto.ForoDto;
import com.sumativa2.Model.Foro;
import com.sumativa2.Model.Usuarios;


public class ForoMapper {

    public static ForoDto toDto(Foro foro) {
        if (foro == null) return null;

        return new ForoDto.Builder()
            .id(foro.getId())
            .comentario(foro.getComentario())
            .fechaCreacion(foro.getFechaCreacion())
            .nombreUsuario(foro.getUsuario().getNombreUsuario())
            .build();
    }

    public static Foro toEntity(ForoDto dto, Usuarios usuario) {
        if (dto == null) return null;

        return new Foro.Builder()
            .id(dto.getId())
            .comentario(dto.getComentario())
            .fechaCreacion(dto.getFechaCreacion())
            .usuario(usuario)
            .build();
    }
}


    
