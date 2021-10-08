/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import sach.platno.Stvorec;

/**
 *
 * @author Dell
 */
public class Sachovnica {
    private Stvorec[][] policka;

    public Sachovnica() {
        this.policka = new Stvorec[8][8];
        int pocitadlo = 0;
        boolean maBytCierna = false;
        for (int x = 0; x < 8; x++) {            
            for (int y = 0; y < 8; y++) {
                Stvorec policko = new Stvorec();
                policko.zmenStranu(Sach.VELKOST);
                policko.posunZvisle(Sach.VELKOST * x - 50);
                policko.posunVodorovne(Sach.VELKOST * y - 61);                
                policko.zobraz();
                this.policka[x][y] = policko;
                
                policko.zmenFarbu("grey");
                if (maBytCierna) {
                    policko.zmenFarbu("green");
                } else {
                    policko.zmenFarbu("white");
                }
                maBytCierna = !maBytCierna;                                   
                pocitadlo++; 
            }   
            if (pocitadlo % 2 == 0) {
                maBytCierna = !maBytCierna;
            }     
            
            
        }
        
    }
    
}
