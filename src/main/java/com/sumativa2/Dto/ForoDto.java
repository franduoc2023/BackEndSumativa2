package com.sumativa2.Dto;

import java.time.LocalDate;

public class ForoDto {
    private Long id;
    private String comentario;
    private LocalDate fechaCreacion;
    private String nombreUsuario;
 
    public ForoDto() {}
     private ForoDto(Builder builder) {
        this.id = builder.id;
        this.comentario = builder.comentario;
        this.fechaCreacion = builder.fechaCreacion;
this.nombreUsuario = builder.nombreUsario;     }

     public Long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
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

    public void setNombreUsuario(String nombreUsario) {
        this.nombreUsuario = nombreUsario;
    }

     public static class Builder {
        private Long id;
        private String comentario;
        private LocalDate fechaCreacion;
        private String nombreUsario;

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

        public Builder nombreUsuario(String nombreUsarios) {
            this.nombreUsario = nombreUsarios;
            return this;
        }

        public ForoDto build() {
            return new ForoDto(this);
        }
    }
}
