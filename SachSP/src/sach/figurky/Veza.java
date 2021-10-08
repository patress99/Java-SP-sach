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
public class Veza extends Figurka {
    
    
    public Veza(int id, int farba, int x, int y) {
    
        this.setFarba(farba);
        this.setRiadok(y);
        this.setStlpec(x);
        this.setId(id);
        
        if (farba == 0) {
            this.setObrazok(new Obrazok("src/White_Rook.png"));
            super.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/White_Rook.png"), x, y);
        }
        
        if (farba == 1) {
            this.setObrazok(new Obrazok("src/Black_Rook.png"));
            super.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/Black_Rook.png"), x, y);
        }
        
        
    }
    
    @Override
    public void posun(int x, int y) {
        super.posun(x, y);
    }
    
    
    @Override
    public boolean platnyTah(Sach sachovnica) {
        
        int riadokNaKontrolu = Math.abs(this.getStlpec() - sachovnica.getCieloveY());
        int stlpecNaKontrolu = Math.abs(this.getRiadok() - sachovnica.getCieloveX());
        
        
        if (this.getRiadok() == sachovnica.getCieloveX() || this.getStlpec() == sachovnica.getCieloveY()) {
            
            //J
            if (this.getStlpec() < sachovnica.getCieloveY()) {
                for (int i = 1; i < riadokNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(this.getStlpec() + i, sachovnica.getCieloveX())) {
                        return false;
                    }
                }
            }
            //sever
            if (this.getStlpec() > sachovnica.getCieloveY()) {
                for (int i = 1; i < riadokNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(this.getStlpec() - i, sachovnica.getCieloveX())) {                        
                        return false;
                    }
                }
            }
            
            //kontrolovanie prekazok na zapad
            if (this.getRiadok() > sachovnica.getCieloveX()) {
                for (int i = 1; i < stlpecNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(sachovnica.getCieloveY(), this.getRiadok() - i)) {                        
                        return false;
                    }
                }
            }
            
            //kontrolovanie prekazok na vychod
            if (this.getRiadok() < sachovnica.getCieloveX()) {
                for (int i = 1; i < stlpecNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(sachovnica.getCieloveY(), this.getRiadok() + i)) {                        
                        return false;
                    }
                }
            }
            return true;

        }
      
        return true;
    }
    
    
}
