package com.sumativa2;

 
import com.sumativa2.Dto.ForoDto;
import com.sumativa2.Dto.UsuariosDto;
import com.sumativa2.Mapper.ForoMapper;
import com.sumativa2.Mapper.UsuariosMapper;
import com.sumativa2.Model.Foro;
import com.sumativa2.Model.Usuarios;
import com.sumativa2.Repository.ForoRepository;
import com.sumativa2.Repository.UsuariosRepository;
import com.sumativa2.Service.UsuariosImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UsuariosImplTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private ForoRepository foroRepository;

    @InjectMocks
    private UsuariosImpl usuariosService;

    private Usuarios usuario;
    private UsuariosDto usuarioDto;

    @BeforeEach
    void setUp() {
        usuario = new Usuarios.Builder()
                .id(1L)
                .nombre("Francisco")
                .apellido("Salinas")
                .nombreUsuario("fran")
                .email("diego@gmail.com")
                .password("123")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .build();

        usuarioDto = new UsuariosDto.Builder()
                .id(1L)
                .nombre("Francisco")
                .apellido("Salinas")
                .nombreUsuario("fran")
                .email("diego@gmail.com")
                .password("123")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .build();
    }

    @Test
    void testCreateUsuarios() {
        when(usuariosRepository.save(any())).thenReturn(usuario);

        UsuariosDto result = usuariosService.createUsuarios(usuarioDto);

        assertEquals("Francisco", result.getNombre());
        verify(usuariosRepository).save(any(Usuarios.class));
    }

    @Test
    void testGetAllUsuarios() {
        when(usuariosRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuariosDto> result = usuariosService.getAllUsuarios();

        assertEquals(1, result.size());
        assertEquals("Francisco", result.get(0).getNombre());
    }

    @Test
    void testGetUser() {
        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuarios result = usuariosService.getUser(1L);

        assertEquals("Francisco", result.getNombre());
    }

    @Test
    void testUpdateUsuario() {
        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuariosRepository.save(any())).thenReturn(usuario);

        UsuariosDto updated = usuariosService.updateUsario(1L, usuarioDto);

        assertEquals("Francisco", updated.getNombre());
    }

    @Test
    void testDeleteUsuarioExists() {
        when(usuariosRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuariosRepository).deleteById(1L);

        usuariosService.deleteUsuario(1L);

        verify(usuariosRepository).deleteById(1L);
    }

    @Test
    void testLoginSuccess() {
        when(usuariosRepository.findByEmail("diego@gmail.com")).thenReturn(Optional.of(usuario));

        Usuarios result = usuariosService.login("diego@gmail.com", "123");

        assertNotNull(result);
        assertEquals("Francisco", result.getNombre());
    }

    @Test
    void testComentarioForo() {
        Foro foro = new Foro.Builder().id(1L).comentario("Original").build();
        ForoDto dto = new ForoDto.Builder().id(1L).comentario("Nuevo comentario").build();

        when(foroRepository.findById(1L)).thenReturn(Optional.of(foro));
        when(foroRepository.save(any())).thenReturn(foro);

        ForoDto result = usuariosService.comentarioForo(dto, usuario);

        assertEquals("Nuevo comentario", result.getComentario());
    }
}
