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
public abstract class Figurka {
    private Obrazok obrazok;
    private int stlpec;
    private int riadok;
    private int id;
    private int farba;
    private int predoslyRiadok;
    private int predoslyStlpec;
    
    public void posunFigurkuNaZaciatku(int id, int farba, Obrazok obrazok, int x, int y) {
        this.id = id;
        this.farba = farba;
        this.stlpec = x;  
        this.riadok = y;
        this.predoslyRiadok = this.riadok;
        this.predoslyStlpec = this.stlpec;
        this.obrazok = obrazok;
        int novyStlpec = (x * Sach.VELKOST) + 31;
        int novyRiadok = (y * Sach.VELKOST) + 31;
        obrazok.zmenPolohu(novyRiadok, novyStlpec);
        obrazok.zobraz();
        
    }

    public Obrazok getObrazok() {
        return this.obrazok;
    }

    public int getId() {
        return this.id;
    }

    public int getPredoslyRiadok() {
        return this.predoslyRiadok;
    }

    public int getPredoslyStlpec() {
        return this.predoslyStlpec;
    }

    public void setObrazok(Obrazok obrazok) {
        this.obrazok = obrazok;
    }

    public void setStlpec(int stlpec) {
        this.stlpec = stlpec;
    }

    public void setRiadok(int riadok) {
        this.riadok = riadok;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFarba(int farba) {
        this.farba = farba;
    }
    
    

    public void posun(int x, int y) {
        this.obrazok.zmenPolohu(x, y);
    }
    
    public int getRiadok() {
        return this.riadok;
    }
    
    public int getStlpec() {
        return this.stlpec;
    }
           
    public int getID() {
        return this.id;
    }

    public void nastavSuradnice(int polohaX, int polohaY) {
        this.predoslyRiadok = this.riadok;
        this.predoslyStlpec = this.stlpec;
        this.stlpec = polohaY;
        this.riadok = polohaX;
    }
    
    public void nastavPredosleSuradnice() {
        this.nastavSuradnice(this.predoslyRiadok, this.predoslyStlpec);
    }
    
    public int getFarba() {
        return this.farba;
    }
    
    public boolean platnyTah(Sach sach) {
        return false;
    }
    
    public boolean jeSach(Sach sach) {
        return false;
    }

    public boolean jeCielovySach(Sach sach, int x, int y) {
        return false;
    }

    public boolean budeStaleSach(Sach sach) {   
        return false;   
    }
    
    public boolean mozeSaPohnut(Sach sach) {
        return true;
    }

        
   
    
   
    
}

