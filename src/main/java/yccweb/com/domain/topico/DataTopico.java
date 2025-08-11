package yccweb.com.domain.topico;
/*
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

import jakarta.validation.constraints.NotBlank;

public record DataTopico(
        @NotBlank String titulo,@NotBlank String mensaje,@NotBlank String autor, @NotBlank String curso

) {
    public DataTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());

    }
}
