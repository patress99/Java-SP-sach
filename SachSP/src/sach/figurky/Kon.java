/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach.figurky;

import sach.Sach;
import sach.platno.Obrazok;




/**
 *
 * @author Paťo
 */
public class Kon extends Figurka {
    public Kon(int id, int farba, int x, int y) {
        this.setStlpec(x);
        this.setRiadok(y);
        this.setId(id);
        this.setFarba(farba);
        if (farba == 0) {            
            this.setObrazok(new Obrazok("src/White_Knight.png"));
            this.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/White_Knight.png"), x, y);              
        }
        
        if (farba == 1) {
            this.setObrazok(new Obrazok("src/Black_Knight.png"));      
            this.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/Black_Knight.png"), x, y);
        
        }
        
    }
    
    @Override
    public void posun(int x, int y) {
        super.posun(x, y);
    }

    
    @Override
    public boolean platnyTah(Sach sachovnica) {
        int riadokNaKontrolu = Math.abs(sachovnica.getCieloveY() - this.getStlpec());
        int stlpecNaKontrolu = Math.abs(sachovnica.getCieloveX() - this.getRiadok());    
        
        return riadokNaKontrolu == 2 && stlpecNaKontrolu == 1 || riadokNaKontrolu == 1 && stlpecNaKontrolu == 2;
        
    }
  
    
   
    
    
    
    
}
