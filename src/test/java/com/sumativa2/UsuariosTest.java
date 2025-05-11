package com.sumativa2;

 
import org.junit.jupiter.api.Test;

import com.sumativa2.Model.Usuarios;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UsuariosTest {

    @Test
    void testConstructorVacio() {
        Usuarios usuario = new Usuarios();
        assertNull(usuario.getId());
        assertNull(usuario.getNombre());
        assertNull(usuario.getApellido());
        assertNull(usuario.getPassword());
        assertNull(usuario.getNombreUsuario());
        assertNull(usuario.getFechaNacimiento());
        assertNull(usuario.getEmail());
    }

    @Test
    void testBuilder() {
        LocalDate fecha = LocalDate.of(1990, 5, 15);

        Usuarios usuario = new Usuarios.Builder()
                .id(1L)
                .nombre("Diego")
                .apellido("Salinas")
                .password("12345")
                .nombreUsuario("diegos")
                .fechaNacimiento(fecha)
                .email("correo@test.com")
                .build();

        assertEquals(1L, usuario.getId());
        assertEquals("Diego", usuario.getNombre());
        assertEquals("Salinas", usuario.getApellido());
        assertEquals("12345", usuario.getPassword());
        assertEquals("diegos", usuario.getNombreUsuario());
        assertEquals(fecha, usuario.getFechaNacimiento());
        assertEquals("correo@test.com", usuario.getEmail());
    }
}

