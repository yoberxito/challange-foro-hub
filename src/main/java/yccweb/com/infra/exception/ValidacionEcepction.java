package yccweb.com.infra.exception;/*
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

public class ValidacionEcepction extends RuntimeException{
    public ValidacionEcepction(String mensaje) {
        super(mensaje);
    }
}
