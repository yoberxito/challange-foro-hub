package yccweb.com.domain.topico;/*
 * Copyright (c) 2025 yober cieza coronel. Todos los derechos reservados.
 *
 * Este archivo es parte de foro-hub.
 *
 * foro-hub es software propietario: no puedes redistribuirlo y/o modificarlo sin el
 * permiso expreso del propietario. Está sujeto a los términos y condiciones
 * que acompañan el uso del software.
 *
 * Cualquier uso no autorizado puede ser sancionado según la ley vigente.
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "topico")
@Getter
@Setter
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String  mensaje;
    @Column(name = "fecha_Creacion")
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String autor;
    private String curso ;

    public Topico(DataTopico dataTopico){
        this.titulo=dataTopico.titulo();
        this.mensaje=dataTopico.mensaje();
        this.fechaCreacion=LocalDateTime.now();
        this.status=true;
        this.autor= dataTopico.autor();
        this.curso= dataTopico.curso();

    }

    public Topico(){

    }

}
