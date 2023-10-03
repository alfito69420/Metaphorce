package com.example.metaphorce.model;

import java.util.Date;

public class Notificacion_Pedido {

    private Long notificacion_pedido_id;
    private String mensaje;
    private Date fecha_hora_creacion;
    private Long pedido_id;
    private Long estado_pedido_id;
    private Long usuario_id;

    public Notificacion_Pedido(Long notificacion_pedido_id, String mensaje, Date fecha_hora_creacion, Long pedido_id, Long estado_pedido_id, Long usuario_id) {
        this.notificacion_pedido_id = notificacion_pedido_id;
        this.mensaje = mensaje;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.pedido_id = pedido_id;
        this.estado_pedido_id = estado_pedido_id;
        this.usuario_id = usuario_id;
    }

    public Long getNotificacion_pedido_id() {
        return notificacion_pedido_id;
    }

    public void setNotificacion_pedido_id(Long notificacion_pedido_id) {
        this.notificacion_pedido_id = notificacion_pedido_id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    public void setFecha_hora_creacion(Date fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
    }

    public Long getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Long pedido_id) {
        this.pedido_id = pedido_id;
    }

    public Long getEstado_pedido_id() {
        return estado_pedido_id;
    }

    public void setEstado_pedido_id(Long estado_pedido_id) {
        this.estado_pedido_id = estado_pedido_id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
} //close class