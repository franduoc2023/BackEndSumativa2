package com.sumativa2.Controller;

 

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;
import com.sumativa2.Repository.ForoRepository;
import com.sumativa2.Repository.UsuariosRepository;
import com.sumativa2.Service.*;
import com.sumativa2.Dto.*;
import com.sumativa2.Model.Foro;
import com.sumativa2.Model.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private ForoRepository foroRepository;

    @Autowired
    private UsuariosService usuariosservice;

    @PostMapping("/registro")
    public ResponseEntity<UsuariosDto> crearUsuario(@RequestBody UsuariosDto usuarioDto) {
        UsuariosDto usuarioGuardado = usuariosservice.createUsuarios(usuarioDto);
        return ResponseEntity.ok(usuarioGuardado);
    }

    @GetMapping
    public ResponseEntity<List<UsuariosDto>> obtenerUsuarios() {
        List<UsuariosDto> usuarios = usuariosservice.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDto> actualizarUsuario(@PathVariable Long id, @RequestBody UsuariosDto usuarioDto) {
        UsuariosDto actualizado = usuariosservice.updateUsario(id, usuarioDto);

        System.out.println("ACTUALIZANDO USUARIO CON ID: " + id);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuariosservice.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDTO, HttpSession session) {
        Usuarios usuario = usuariosservice.login(loginDTO.getEmail(), loginDTO.getPassword());

        if (usuario != null) {
            session.setAttribute("usuarioLogueado", usuario);
            return ResponseEntity.ok("loginId " + usuario.getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @GetMapping("/foro")
    public ResponseEntity<List<ForoDto>> obtenerForosDelUsuario(HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<ForoDto> forosDelUsuario = usuariosservice.getForosPorUsuario(usuario);
        return ResponseEntity.ok(forosDelUsuario);
    }

    @PostMapping("/foro")
    public ResponseEntity<String> agregarComentarioAlForo(@RequestBody ForoDto foroDto, HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" ");
        }

        Foro foro = new Foro.Builder()
            .comentario(foroDto.getComentario())
            .fechaCreacion(LocalDate.now())
            .usuario(usuario)
            .build();

        foroRepository.save(foro);

        return ResponseEntity.ok(" ");
    }
}

