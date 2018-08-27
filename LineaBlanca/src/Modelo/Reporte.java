/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Niclec
 */
public abstract class Reporte {
    LocalDate tiempo_reporte;
    
    public Reporte(){
        
    }

    public LocalDate getTiempo_reporte() {
        return tiempo_reporte;
    }

    public void setTiempo_reporte(LocalDate tiempo_reporte) {
        this.tiempo_reporte = tiempo_reporte;
    }
    
    
}
