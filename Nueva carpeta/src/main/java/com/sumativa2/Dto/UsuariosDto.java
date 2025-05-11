package com.sumativa2.Dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

public class UsuariosDto {

    private Long id;
    @NotNull
    private String nombre;
    private String apellido;
    @NotNull
    private String password;
    @NotNull
    private String nombreUsuario;
    private LocalDate fechaNacimiento;
    @NotNull
    private String email;
    public UsuariosDto() {
    }
     private UsuariosDto(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.password = builder.password;
        this.nombreUsuario = builder.nombreUsuario;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.email = builder.email;
    }

     public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

     public static class Builder {
        private Long id;
        private String nombre;
        private String apellido;
        private String password;
        private String nombreUsuario;
        private LocalDate fechaNacimiento;
        private String email;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder nombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
            return this;
        }

        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UsuariosDto build() {
            return new UsuariosDto(this);
        }
    }
}

