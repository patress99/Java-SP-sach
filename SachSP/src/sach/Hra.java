/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import sach.platno.Manazer;

/**
 *
 * @author Dell
 */
public class Hra {
    
    
    private final Manazer manazer;

    public Hra() {
        Sach.getInstancia();
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(Sach.getInstancia());
    }
    
    
    public static void main(String[] args) {
        Hra hra = new Hra();
       
    }
}
