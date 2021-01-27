package com.example.basededatos;

public class ClassListItemsEvents {

    private String idEvento, tipoEvento, descripcionEvento, fecha, idUsuario;

    public ClassListItemsEvents(String idEvento, String tipoEvento, String descripcionEvento, String fecha, String idUsuario)
    {
        this.idEvento=idEvento;
        this.tipoEvento = tipoEvento;
        this.descripcionEvento = descripcionEvento;
        this.fecha=fecha;
        this.idUsuario=idUsuario;
    }

    public ClassListItemsEvents()
    {

    }

    public String getIdEvento(){return idEvento;}

    public void setIdEvento(String idEvento){this.idEvento=idEvento;}

    public String getTipoEvento(){return tipoEvento;}

    public void setTipoEvento(String tipoEvento){this.tipoEvento=tipoEvento;}

    public  String getDescripcionEvento(){return descripcionEvento;}

    public void setDescripcionEvento(String descripcionEvento){this.descripcionEvento=descripcionEvento;}

    public String getFecha(){return  fecha;}

    public void setFecha(String fecha){this.fecha=fecha;}

    public String getIdUsuario(){return idUsuario;}

    public void setIdUsuario(String idUsuario){this.idUsuario=idUsuario;}


}
