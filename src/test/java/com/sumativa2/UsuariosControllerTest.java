package com.sumativa2;

import com.fasterxml.jackson.databind.ObjectMapper;
 import com.sumativa2.Model.Usuarios;
import com.sumativa2.Repository.ForoRepository;
import com.sumativa2.Service.UsuariosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
  import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(com.sumativa2.TestSecurityConfig.class)
public class UsuariosControllerTest {
   
 

 
     


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuariosService usuariosService;

    @MockBean
    private ForoRepository foroRepository;

    @Test
    void testPostRegistro_isOk() throws Exception {
        Map<String, String> datos = new HashMap<>();
        datos.put("nombre", "Diego");
        datos.put("email", "correo@test.com");

        mockMvc.perform(post("/usuarios/registro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUsuarios_isOk() throws Exception {
        Usuarios mockUsuario = new Usuarios.Builder()
                .id(1L)
                .nombre("Diego")
                .email("correo@test.com")
                .build();

when(usuariosService.getAllUsuarios()).thenReturn(List.of());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usuarioLogueado", mockUsuario);

        mockMvc.perform(get("/usuarios").session(session))
                .andExpect(status().isOk());
    }






    @Test
    void testGetUsuarioPorId_isOk() throws Exception {
        Usuarios mockUsuario = new Usuarios.Builder()
                .id(1L)
                .email("test@test.com")
                .build();

        when(usuariosService.getUser(1L)).thenReturn(mockUsuario);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testPostLogin_isUnauthorized() throws Exception {
        Map<String, String> login = new HashMap<>();
        login.put("email", "test@test.com");
        login.put("password", "123");

        mockMvc.perform(post("/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testPostForo_isOk() throws Exception {
        Map<String, String> comentario = new HashMap<>();
        comentario.put("comentario", "Hola foro");

        Usuarios usuarioLogueado = new Usuarios.Builder()
                .id(1L)
                .email("test@test.com")
                .build();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usuarioLogueado", usuarioLogueado);

        mockMvc.perform(post("/usuarios/foro")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comentario)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetForo_isOk() throws Exception {
        mockMvc.perform(get("/usuarios/foro"))
                .andExpect(status().isOk());
    }
}
