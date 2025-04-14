package com.sumativa2.Model;

 
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "password")
    private String password;

    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "email")
    private String email;

    private Usuarios(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.password = builder.password;
        this.nombreUsuario = builder.nombreUsuario;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.email = builder.email;
    }

    public Usuarios() {
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

        public Usuarios build() {
            return new Usuarios(this);
        }

         public String getNombreUsuario() {
            return nombreUsuario;
        }
    }
}

