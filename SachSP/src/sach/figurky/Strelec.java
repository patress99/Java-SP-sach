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
public class Strelec extends Figurka {
    public Strelec(int id, int farba, int x, int y) {
        this.setStlpec(x);
        this.setRiadok(y);
        this.setFarba(farba);
        this.setId(id);
        if (farba == 0) {
                     
            this.setObrazok(new Obrazok("src/White_Bishop.png"));
            super.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/White_Bishop.png"), x, y);              
        }
        
        if (farba == 1) {
            this.setObrazok(new Obrazok("src/Black_Bishop.png"));
            super.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/Black_Bishop.png"), x, y);   
        
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

        if (riadokNaKontrolu == stlpecNaKontrolu) {
           
            //kontrolovanie prekazky JV
            if (sachovnica.getCieloveX() > this.getRiadok() && sachovnica.getCieloveY() > this.getStlpec()) {
                
                int pocetFiguriek = 0;
                for (int i = 1; i <= riadokNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(this.getStlpec() + i, this.getRiadok() + i)) {
                        pocetFiguriek++;
                    }
                }
                
                for (int i = 1; i <= riadokNaKontrolu; i++) {   
                    
                    if (sachovnica.mozeVyhodit(sachovnica.getCieloveY(), sachovnica.getCieloveX()) && pocetFiguriek == 1) {
                        return true;
                    }
                    
                    if (sachovnica.jeTamFigurka(this.getStlpec() + i, this.getRiadok() + i)) {                   
                        return false;                        
                    }
                    
                }
            }
            
            //kontrolovanie prekazky SZ
            if (sachovnica.getCieloveX() < this.getRiadok() && sachovnica.getCieloveY() < this.getStlpec()) {
                
                int pocetFiguriek = 0;
                for (int i = 1; i <= riadokNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(this.getStlpec() - i, this.getRiadok() - i)) {
                        pocetFiguriek++;
                    }
                }
                
                for (int i = 1; i <= riadokNaKontrolu; i++) {
                    if (sachovnica.mozeVyhodit(sachovnica.getCieloveY(), sachovnica.getCieloveX()) && pocetFiguriek == 1) {
                        return true;
                    }
                    
                    if (sachovnica.jeTamFigurka(this.getStlpec() - i, this.getRiadok() - i)) {                        
                        return false;
                    }
                }
            }
            
            
            
            //kontrolovanie prekazy JZ
            if (sachovnica.getCieloveX() < this.getRiadok() && sachovnica.getCieloveY() > this.getStlpec()) {
                
                
                int pocetFiguriek = 0;
                for (int i = 1; i <= riadokNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(this.getStlpec() + i, this.getRiadok() - i)) {
                        pocetFiguriek++;
                    }
                }
                
                for (int i = 1; i <= riadokNaKontrolu; i++) {
                    if (sachovnica.mozeVyhodit(sachovnica.getCieloveY(), sachovnica.getCieloveX()) && pocetFiguriek == 1) {
                        return true;
                    }
                    
                    if (sachovnica.jeTamFigurka(this.getStlpec() + i, this.getRiadok() - i)) {
                        return false;
                    }
                }
            }
            
            
            //kontrolovanie prekazky SV
            if (sachovnica.getCieloveX() > this.getRiadok() && sachovnica.getCieloveY() < this.getStlpec()) { 
                
                //zistenie poctu figurok na ceste
                int pocetFiguriek = 0;
                for (int i = 1; i <= riadokNaKontrolu; i++) {
                    if (sachovnica.jeTamFigurka(this.getStlpec() - i, this.getRiadok() + i)) {
                        pocetFiguriek++;
                    }
                }
                // zistenie mozneho tahu 
                for (int i = 1; i <= riadokNaKontrolu; i++) {     
                    if (sachovnica.mozeVyhodit(sachovnica.getCieloveY(), sachovnica.getCieloveX()) && pocetFiguriek == 1) {                        
                        return true;                        
                    }
                    
                    if (sachovnica.jeTamFigurka(this.getStlpec() - i, this.getRiadok() + i)) {
                        return false;
                    }
                }
            }
            
            
            return true;
        }
        
        return false;
        
        
        
         
    }

  
    

    
    
     
    
    
}
