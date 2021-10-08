/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sach;

import sach.figurky.Figurka;
import sach.figurky.Kon;
import sach.figurky.Kral;
import sach.figurky.Kralovna;
import sach.figurky.Pesiak;
import sach.figurky.Strelec;
import sach.figurky.Veza;
/**
 *
 * @author Paťo
 */
public class Sach {
    private static final Sach sach = new Sach();
    public static final int VELKOST = 61;  
    private final Figurka[] figurky;
    private int index;
    private boolean jeOznacena;
    private int farba;
    private int cieloveX;
    private int cieloveY;
    private int pocitadlo;
    private int pocetVyhodenych;
    private int posunY;
    private boolean jeOznacenyKral;
    private boolean budeVSachu;
    
    public static Sach getInstancia() {
        return Sach.sach;
    }
    private boolean jeVSachu;
    
    
    private Sach() {       
        this.budeVSachu = false;
        this.jeVSachu = false;
        this.jeOznacenyKral = false;
        this.posunY = 0;
        this.pocetVyhodenych = 0;
        this.pocitadlo = 0;
        this.cieloveX = 0;
        this.cieloveY = 0;
        this.farba = -1;
        this.figurky = new Figurka[32];
        this.index = -1;
        this.jeOznacena = false;      
        System.out.println("Zacina biely");
        Sachovnica s1 = new Sachovnica();
        this.rozmiestniFigurky();
        
    }
    
    public final void rozmiestniFigurky() {      
        // biela farba = 0, cierna = 1
        for (int y = 0; y < 8; y++) {    
            this.figurky[16 + y] = new Pesiak(16 + y, 0, 6, y);
            this.figurky[24 + y] = new Pesiak(24 + y, 1, 1, y);        
        }
        
        this.figurky[0] = new Kon(0, 0, 7, 1);
        this.figurky[1] = new Kon(1, 0, 7, 6);
        this.figurky[2] = new Kon(2, 1, 0, 1);
        this.figurky[3] = new Kon(3, 1, 0, 6);        
        
        this.figurky[4] = new Veza(4, 0, 7, 0);
        this.figurky[5] = new Veza(5, 0, 7, 7);
        this.figurky[6] = new Veza(6, 1, 0, 7);
        this.figurky[7] = new Veza(7, 1, 0, 0);
        
        this.figurky[8] = new Strelec(8, 0, 7, 2);
        this.figurky[9] = new Strelec(9, 0, 7, 5);
        this.figurky[10] = new Strelec(10, 1, 0, 2);
        this.figurky[11] = new Strelec(11, 1, 0, 5);
        
        this.figurky[12] = new Kralovna(12, 0, 7, 3);
        this.figurky[13] = new Kralovna(13, 1, 0, 3);
        
        this.figurky[14] = new Kral(14, 0, 7, 4);
        this.figurky[15] = new Kral(15, 1, 0, 4);
        
    }
    
    public void vyberSuradnice(int x, int y) {
        int polohaX = x / Sach.VELKOST;
        int polohaY = y / Sach.VELKOST;
        this.cieloveX = polohaX;
        this.cieloveY = polohaY; 
        if (polohaX >= 8) {
            return;
        }        
        //System.out.println(polohaX + " " + polohaY);
        this.spravujFigurku(polohaY, polohaX); 
        
        
        
    }
    
    public void spravujFigurku(int polohaY, int polohaX) {       
        int stredX = polohaX * Sach.VELKOST + 30;
        int stredY = polohaY * Sach.VELKOST + 30;
        if (!this.jeOznacena) {
            for (Figurka figurky1 : this.figurky) {                      
                if (figurky1.getStlpec() == polohaY && figurky1.getRiadok() == polohaX) { 
                    this.jeOznacenyKral = false;
                    this.farba = figurky1.getFarba();
                    this.index = figurky1.getID(); 
                    
                    if (figurky1.getID() == 14 || figurky1.getID() == 15) {
                        this.jeOznacenyKral = true;
                    }
                    
                    if (this.pocitadlo % 2 == 0 && this.farba == 1 || this.pocitadlo % 2 == 1 && this.farba == 0) {
                        break;
                    }    
                    System.out.println(figurky1);                    
                }
                
                if (this.jeTamFigurkaInejFarby(polohaY, polohaX)) {
                    if (this.pocitadlo % 2 == 0 && this.farba == 0) {
                        this.vyhodFigurku(polohaY, polohaX, stredX, stredY, 14);
                        if (this.figurky[15].jeSach(Sach.getInstancia())) {
                            this.jeVSachu = true;
                        }
                        break;
                    }
                    if (this.pocitadlo % 2 == 1 && this.farba == 1) {
                        this.vyhodFigurku(polohaY, polohaX, stredX, stredY, 15);
                        if (this.figurky[14].jeSach(Sach.getInstancia())) {
                            this.jeVSachu = true;
                        }
                        break;
                    }
                }    
                
                if (!this.jeTamFigurka(polohaY, polohaX)) {
                    if (this.pocitadlo % 2 == 1 && this.farba == 1) {                                             
                        this.vykonajPohybFigurky(15, stredX, stredY, polohaY, polohaX);
                        break;
                    } 
                    
                    if (this.pocitadlo % 2 == 0 && this.farba == 0) {  
                        this.vykonajPohybFigurky(14, stredX, stredY, polohaY, polohaX);
                        break;
                    }
                }
            }
        } 
        
    }   
    
    
    public boolean jeTamFigurka(int polohaY, int polohaX) {
        for (Figurka figurky1 : this.figurky) {
            if (figurky1.getStlpec() == polohaY && figurky1.getRiadok() == polohaX) {
                return true;                    
            }     
        }
        return false;
    }
    
    public boolean jeTamFigurkaInejFarby(int polohaY, int polohaX) {
        for (Figurka figurky1 : this.figurky) {
            if (figurky1.getStlpec() == polohaY && figurky1.getRiadok() == polohaX && figurky1.getFarba() != this.farba) {
                return true;                    
            } 
        }
        return false;
    }
    
    public boolean mozeVyhodit(int polohaY, int polohaX) {        
        for (Figurka figurky1 : this.figurky) {
            if (figurky1.getStlpec() == polohaY && figurky1.getRiadok() == polohaX) {        
                //ak je tam figurka rovnakej farby nemozem vyhodit
                if (figurky1.getFarba() == this.farba) {
                    return false;
                }
            }            
            
            if (!this.jeTamFigurka(polohaY, polohaX)) {                
                return false;
            }      
        }
        return true;
    }
    
    public void vyhodFigurku(int polohaY, int polohaX, int stredX, int stredY, int indexKrala) {
        System.out.println(polohaY + " " + polohaX);
        if (this.jeTamFigurkaInejFarby(polohaY, polohaX) && this.figurky[this.index].platnyTah(Sach.getInstancia())) {
            for (Figurka figurky1 : this.figurky) {
                if (figurky1.getStlpec() == polohaY && figurky1.getRiadok() == polohaX) {                      
                    this.vykonajPohybFigurky(indexKrala, stredX, stredY, polohaY, polohaX);   
                    if (this.budeVSachu) {
                        return;
                    }
                    
                    this.figurky[this.index].nastavSuradnice(polohaX, polohaY);
                    this.figurky[this.index].posun(stredX, stredY);
                    this.index = figurky1.getID();
                    this.figurky[this.index].posun((this.pocetVyhodenych * 61) +  518, (this.posunY * 61) + 30);
                    this.pocetVyhodenych++;                    
                    this.figurky[this.index].nastavSuradnice(17, 17); 
                    this.index = -1;
                    this.pocitadlo++;
                    this.jeOznacena = false;    
                    this.jeVSachu = false;
                    if (this.pocetVyhodenych % 4 == 0) {
                        this.pocetVyhodenych = 0;
                        this.posunY++; 
                    }  
                    
                }
            }            
        }
    }
    
    public void tahHraca(int polohaY, int polohaX, int stredX, int stredY) { 
        //tah bieleho
        if (this.farba == 0 && this.pocitadlo % 2 == 0) { 
            if (this.jeOznacena && !this.jeTamFigurka(polohaY, polohaX)) { 
                this.figurky[this.index].nastavSuradnice(polohaX, polohaY); 
                this.figurky[this.index].posun(stredX, stredY); 
                if (this.figurky[15].jeSach(Sach.getInstancia())) {
                    this.jeVSachu = true;
                }
                this.jeOznacena = false;                    
                this.pocitadlo++;  
                System.out.println("ide cierny");     
                return;                
            }
        }
        
        //tah cierneho       
        if (this.farba == 1 && this.pocitadlo % 2 == 1) {
            if (this.jeOznacena && !this.jeTamFigurka(polohaY, polohaX)) {
                this.figurky[this.index].posun(stredX, stredY);                
                this.figurky[this.index].nastavSuradnice(polohaX, polohaY);    
                if (this.figurky[14].jeSach(Sach.getInstancia())) {
                    this.jeVSachu = true;
                }
                this.jeOznacena = false; 
                this.pocitadlo++;                          
                System.out.println("ide biely");
            }
        }
    }
    
    public Figurka zistiFigurku(int y, int x) {
        for (Figurka figurky1 : this.figurky) {
            if (figurky1.getStlpec() == y && figurky1.getRiadok() == x) { 
                return figurky1;
            }
        } 
        return null;
    }
    
    //kontroluje ci figurka moze vykonat cielovy pohyb, ak moze pohyb vykona
    public void vykonajPohybFigurky(int indexKrala, int stredX, int stredY, int polohaY, int polohaX) {
        this.budeVSachu = false;
        if (this.pocitadlo % 2 == 1 && this.farba == 0 || this.pocitadlo % 2 == 0 && this.farba == 1) {
            if (!this.figurky[this.index].platnyTah(Sach.getInstancia())) {
                return;
            }                        
            this.jeOznacena = false;
            return;
        }
        
        if (this.jeOznacenyKral && this.figurky[indexKrala].jeCielovySach(Sach.getInstancia(), this.cieloveX, this.cieloveY)) {
            this.budeVSachu = true;
            return;
        }
        if (this.jeOznacenyKral && this.jeVSachu && !this.figurky[indexKrala].jeCielovySach(Sach.getInstancia(), this.cieloveX, this.cieloveY)) {
            this.jeVSachu = false;
        }
        if (!this.figurky[this.index].platnyTah(Sach.getInstancia())) {
            return;
        }

        if (this.jeVSachu && !this.jeOznacenyKral) {
            this.figurky[this.index].nastavSuradnice(this.cieloveX, this.cieloveY);
            if (!this.figurky[indexKrala].budeStaleSach(Sach.getInstancia())) {
                this.figurky[this.index].posun(stredX, stredY);
                this.pocitadlo++;
                this.jeVSachu = false;
                return;
            }
            this.figurky[this.index].nastavPredosleSuradnice();
            return;
        }
        
        this.jeOznacena = true; 
        this.figurky[this.index].nastavSuradnice(this.cieloveX, this.cieloveY);
        if (!this.figurky[indexKrala].mozeSaPohnut(Sach.getInstancia())) {
            this.figurky[this.index].nastavPredosleSuradnice();
            this.jeOznacena = false;
            this.budeVSachu = true;
            return;
        }
        this.figurky[this.index].nastavPredosleSuradnice();
        this.tahHraca(polohaY, polohaX, stredX, stredY); 
        this.jeOznacenyKral = false;
    }
    
    public int getCieloveX() {
        return this.cieloveX;
    }

    public int getCieloveY() {
        return this.cieloveY;
    }

    public boolean jeSach() {
        return this.jeVSachu;
    }
    
    
    
  
}
    