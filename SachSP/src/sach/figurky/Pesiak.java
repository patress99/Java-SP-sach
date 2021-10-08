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
public class Pesiak extends Figurka {
    private boolean prvyTah;
    public Pesiak(int id, int farba, int x, int y) {
        this.prvyTah = true;
        this.setStlpec(x);
        this.setRiadok(y);
        this.setId(id);
        this.setFarba(farba);
        if (farba == 0) {
                     
            this.setObrazok(new Obrazok("src/White_Pawn.png"));
            this.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/White_Pawn.png"), x, y);              
        }
        
        if (farba == 1) {
            this.setObrazok(new Obrazok("src/Black_Pawn.png"));
            this.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/Black_Pawn.png"), x, y);   
        
        }
        
    }
    
    @Override
    public void posun(int x, int y) {
        super.posun(x, y);
       
    }
    
    @Override
    public boolean platnyTah(Sach sachovnica) {
        
        //kontrola pohybu cierneho pesiaka        
        if (this.getFarba() == 0) {
            
            if (this.prvyTah && sachovnica.getCieloveY() + 2 == this.getStlpec() && sachovnica.getCieloveX() == this.getRiadok()) {
                this.prvyTah = false;
                return true;
            }
            
            if (sachovnica.getCieloveY() + 1 == this.getStlpec() && sachovnica.getCieloveX() == this.getRiadok()) {               
                this.prvyTah = false;
                return !sachovnica.jeTamFigurka(this.getStlpec() - 1, this.getRiadok());
            }    
            
            if (sachovnica.getCieloveX() == this.getRiadok() - 1 && sachovnica.getCieloveY() == this.getStlpec() - 1 && sachovnica.jeTamFigurkaInejFarby(this.getStlpec() - 1, this.getRiadok() - 1)) {
                return true;
            }
                
            else if (sachovnica.getCieloveX() == this.getRiadok() + 1 && sachovnica.getCieloveY() == this.getStlpec() - 1 && sachovnica.jeTamFigurkaInejFarby(this.getStlpec() - 1, this.getRiadok() + 1)) {
                return true;
            }
                
                
            
        }
        
        
        
        
        //kontrola pohybu bieleho pesiaka
        if (this.getFarba() == 1) {    
            if (this.prvyTah && sachovnica.getCieloveY() - 2 == this.getStlpec() && sachovnica.getCieloveX() == this.getRiadok()) {                
                this.prvyTah = false;
                return true;
            }
            
            if (sachovnica.getCieloveY() - 1 == this.getStlpec() && sachovnica.getCieloveX() == this.getRiadok()) {               
                this.prvyTah = false;                
                return !sachovnica.jeTamFigurka(this.getStlpec() + 1, this.getRiadok());
            }  
            
            if (sachovnica.getCieloveX() == this.getRiadok() + 1 && sachovnica.getCieloveY() == this.getStlpec() + 1 && sachovnica.jeTamFigurkaInejFarby(this.getStlpec() + 1, this.getRiadok() + 1)) {
                return true;
            } else if (sachovnica.getCieloveX() == this.getRiadok() - 1 && sachovnica.getCieloveY() == this.getStlpec() + 1 && sachovnica.jeTamFigurkaInejFarby(this.getStlpec() + 1, this.getRiadok() - 1)) {
                return true;
            }
              
              
        }
        
        return false;
    }
    
    

   
 
}
