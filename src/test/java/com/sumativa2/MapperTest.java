package com.sumativa2;

 import com.sumativa2.Dto.UsuariosDto;
import com.sumativa2.Mapper.UsuariosMapper;
import com.sumativa2.Model.Usuarios;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MapperTest {

    @Test
    void testToDto() {
        Usuarios usuario = new Usuarios.Builder()
                .id(1L)
                .nombre("Diego")
                .apellido("Salinas")
                .password("1234")
                .nombreUsuario("diegos")
                .fechaNacimiento(LocalDate.of(2000, 1, 1))
                .email("diego@test.com")
                .build();

        UsuariosDto dto = UsuariosMapper.toDto(usuario);

        assertEquals(1L, dto.getId());
        assertEquals("Diego", dto.getNombre());
        assertEquals("Salinas", dto.getApellido());
        assertEquals("1234", dto.getPassword());
        assertEquals("diegos", dto.getNombreUsuario());
        assertEquals(LocalDate.of(2000, 1, 1), dto.getFechaNacimiento());
        assertEquals("diego@test.com", dto.getEmail());
    }

    @Test
    void testToEntity() {
        UsuariosDto dto = new UsuariosDto.Builder()
                .id(2L)
                .nombre("Francisco")
                .apellido("Martínez")
                .password("abcd")
                .nombreUsuario("franma")
                .fechaNacimiento(LocalDate.of(1995, 5, 20))
                .email("fran@test.com")
                .build();

        Usuarios usuario = UsuariosMapper.toEntity(dto);

        assertEquals(2L, usuario.getId());
        assertEquals("Francisco", usuario.getNombre());
        assertEquals("Martínez", usuario.getApellido());
        assertEquals("abcd", usuario.getPassword());
        assertEquals("franma", usuario.getNombreUsuario());
        assertEquals(LocalDate.of(1995, 5, 20), usuario.getFechaNacimiento());
        assertEquals("fran@test.com", usuario.getEmail());
    }
}
