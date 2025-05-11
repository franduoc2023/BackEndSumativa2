package com.sumativa2;

import com.sumativa2.Model.Foro;
import com.sumativa2.Model.Usuarios;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ForoTest {

    @Test
    void testForoBuilderYGetters() {
        Usuarios usuario = new Usuarios.Builder()
                .id(1L)
                .nombre("Diego")
                .email("diego@example.com")
                .build();

        Foro foro = new Foro.Builder()
                .id(100L)
                .comentario("Este es un comentario de prueba")
                .fechaCreacion(LocalDate.of(2025, 5, 11))
                .usuario(usuario)
                .build();

        assertEquals(100L, foro.getId());
        assertEquals("Este es un comentario de prueba", foro.getComentario());
        assertEquals(LocalDate.of(2025, 5, 11), foro.getFechaCreacion());
        assertEquals(usuario, foro.getUsuario());
    }

    @Test
    void testSetters() {
        Foro foro = new Foro.Builder().build();

        foro.setId(200L);
        foro.setComentario("Nuevo comentario");
        foro.setFechaCreacion(LocalDate.now());

        Usuarios usuario = new Usuarios.Builder().id(2L).nombre("Francisco").build();
        foro.setUsuario(usuario);

        assertEquals(200L, foro.getId());
        assertEquals("Nuevo comentario", foro.getComentario());
        assertEquals(LocalDate.now(), foro.getFechaCreacion());
        assertEquals(usuario, foro.getUsuario());
    }
}