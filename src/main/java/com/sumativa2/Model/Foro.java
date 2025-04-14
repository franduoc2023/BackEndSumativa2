package com.sumativa2.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "foro")
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;

    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuario;

     private Foro(Builder builder) {
        this.id = builder.id;
        this.comentario = builder.comentario;
        this.fechaCreacion = builder.fechaCreacion;
        this.usuario = builder.usuario;
    }

     public Long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

     public void setId(Long id) {
        this.id = id;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public static class Builder {
        private Long id;
        private String comentario;
        private LocalDate fechaCreacion;
        private Usuarios usuario;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder comentario(String comentario) {
            this.comentario = comentario;
            return this;
        }

        public Builder fechaCreacion(LocalDate fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
            return this;
        }

        public Builder usuario(Usuarios usuario) {
            this.usuario = usuario;
            return this;
        }

        public Foro build() {
            return new Foro(this);
        }
    }
}
