package com.example.basededatos;

public class ClassListItems {

    private String idReporte, idUsuario,motivo, descripcion,fecha,estadoRepo;

    public ClassListItems(String idReporte, String idUsuario,String motivo, String decripcion, String fecha, String estadoRepo)
    {
        this.idReporte = idReporte;
        this.idUsuario = idUsuario;
        this.motivo=motivo;
        this.descripcion=decripcion;
        this.fecha=fecha;
        this.estadoRepo=estadoRepo;
    }
    public ClassListItems()
    {

    }
    public String getIdReporte()
    {
        return idReporte;
    }

    public void setidReporte(String idReporte)
    {
        this.idReporte=idReporte;
    }

    public String getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario)
    {
        this.idUsuario=idUsuario;
    }

    public String getMotivo(){return motivo;}

    public void setMotivo(String motivo){this.motivo=motivo;}

    public String getDescripcion(){return descripcion;}

    public void setDescripcion(String descripcion){this.descripcion=descripcion;}

    public String getFecha(){return  fecha;}

    public void setFecha(String descripcion){this.fecha=fecha;}

    public String getEstadoRepo(){return estadoRepo;}

    public void setEstadoRepo(String descripcion){this.estadoRepo=estadoRepo;}
}
