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
public class Kral extends Figurka {
    private boolean sachZprava;
    private boolean jeVSachu;
    private boolean sachZdola;
    private boolean sachZhora;
    private boolean sachZlava;
    private boolean sachSV;
    private boolean sachSZ;
    private boolean sachJV;
    private boolean sachJZ;
    public Kral(int id, int farba, int x, int y) {
        this.sachJV = false;
        this.sachJZ = false;
        this.sachSV = false;
        this.sachSZ = false;
        this.sachZlava = false;
        this.sachZprava = false;
        this.sachZdola = false;
        this.sachZhora = false;
        this.setStlpec(x);
        this.setFarba(farba);
        this.jeVSachu = false;
        this.setRiadok(y);
        this.setId(id);
        if (farba == 0) {                     
            this.setObrazok(new Obrazok("src/White_King.png"));
            this.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/White_King.png"), x, y);              
        }
        
        if (farba == 1) {
            this.setObrazok(new Obrazok("src/Black_King.png"));
            this.posunFigurkuNaZaciatku(id, farba, new Obrazok("src/Black_King.png"), x, y);
        
        }
        
    }
    
    @Override
    public void posun(int x, int y) {
        super.posun(x, y);
    }
    
    @Override
    public boolean platnyTah(Sach sach) {
        int x = this.getRiadok();
        int y = this.getStlpec();
        int pozX [] = { x,x,x+1,x+1,x+1,x-1,x-1,x-1 };
	int pozY[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
        
        for (int i = 0; i < 8; i++) {   
            if (sach.getCieloveX() == pozX[i] && sach.getCieloveY() == pozY[i]) {
                return true;
            }                    
        }
        return true;
    }
    
    @Override
    public boolean jeSach(Sach sach) {  
        this.sachSV = false;
        this.sachSZ = false;
        this.sachJV = false;
        this.sachJZ = false;
        this.sachZprava = false;
        this.sachZprava = false;
        this.sachZdola = false;
        this.sachZhora = false;
        
        
        //zistovanie sachu v smere veze         
        int pocitadloFiguriekNadol = 0; 
        int pocitadloFiguriekNahor = 0;
        int pocitadloFiguriekNapravo = 0;
        int pocitadloFiguriekNalavo = 0;
        //hladanie sachu napravo od krala
        for (int i = this.getRiadok() + 1; i < 8; i++) {  
            //zistuje pocet figuriek na svojej ceste
            if (sach.jeTamFigurka(this.getStlpec(), i)) {
                pocitadloFiguriekNapravo++;
                
                if (pocitadloFiguriekNapravo > 1) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(this.getStlpec(), i)) {
                break;
            }

            if (sach.zistiFigurku(this.getStlpec(), i) instanceof Veza || sach.zistiFigurku(this.getStlpec(), i) instanceof Kralovna) {
                this.sachZprava = true;
                System.out.println("Sach zprava");
                return true;
            }
        }
        
        //hladanie sachu nalavo od krala
        for (int i = this.getRiadok() - 1; i >= 0; i--) {    
            if (sach.jeTamFigurka(this.getStlpec(), i)) {
                pocitadloFiguriekNalavo++;
                if (pocitadloFiguriekNalavo > 1) {
                    break;
                }
            }
            if (sach.jeTamFigurkaInejFarby(this.getStlpec(), i)) {
                break;
            }
            
            if (sach.zistiFigurku(this.getStlpec(), i) instanceof Veza || sach.zistiFigurku(this.getStlpec(), i) instanceof Kralovna) {                
                this.sachZlava = true;
                System.out.println("Sach zlava");
                return true;
            }

        }
        
        //hladanie sachu nahor od krala
        for (int i = this.getStlpec() - 1; i >= 0; i--) {   
            if (sach.jeTamFigurka(i, this.getRiadok())) {
                pocitadloFiguriekNahor++;
                if (pocitadloFiguriekNahor > 1) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(i, this.getRiadok())) {
                break;
            }

            if (sach.zistiFigurku(i, this.getRiadok()) instanceof Veza || sach.zistiFigurku(i, this.getRiadok()) instanceof Kralovna) {                
                this.sachZhora = true;
                System.out.println("Sach zhora");
                return true;
            } 

        }
        
        //hladanie sachu nadol od krala
        for (int i = this.getStlpec() + 1; i < 8; i++) {               
            if (sach.jeTamFigurka(i, this.getRiadok())) {
                pocitadloFiguriekNadol++;
                if (pocitadloFiguriekNadol > 1) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(i, this.getRiadok())) {               
                break;
            }

            if (sach.zistiFigurku(i, this.getRiadok()) instanceof Veza || sach.zistiFigurku(i, this.getRiadok()) instanceof Kralovna) {                
                this.sachZdola = true;
                System.out.println("Sach zdola");
                return true;
                
            } 
        }
        
        
        int pfSZ = 0;
        int pfSV = 0;
        int pfJZ = 0;
        int pfJV = 0;
        
        // zistovanie sachu v smere strelca       
        for (int i = 1; i <= 8 ; i++) {
            //kontrola sachu na severozapad
            if (sach.jeTamFigurka(this.getStlpec() - i, this.getRiadok() - i)) {
                pfSZ++;
                if (pfSZ > 1) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() - i)) {
                break;
            }

            if (sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() - i) instanceof Strelec || sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() - i) instanceof Kralovna) {                        
                System.out.println("Sach zo severozapadu");
                this.sachSZ = true;
                return true;
            }
        }
        
        
        for (int i = 1; i <= 8; i++) {
            //kontrola sachu z juhovychodu
            if (sach.jeTamFigurka(this.getStlpec() + i, this.getRiadok() + i)) {
                pfJV++;
                if (pfJV > 1) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() + i)) {
                break;
            }
            
            if (sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() + i) instanceof Strelec || sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() + i) instanceof Kralovna) {                
                this.sachJV = true;
                System.out.println("Sach z juhovychodu");
                return true;
            }
            
        }
            
            
        for (int i = 1; i <= 8; i++) {

            //kontrola sachu z juhozapadu
            if (sach.jeTamFigurka(this.getStlpec() + i, this.getRiadok() - i)) {
                pfJZ++;
                if (pfJZ > 1) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() - i)) {
                break;
            }
            
            if (sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() - i) instanceof Strelec || sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() - i) instanceof Kralovna) {                
                this.sachJZ = true;
                System.out.println("Sach z juhozapadu");
                return true;
            }
        }
        
        
            
        for (int i = 1; i <= 8; i++) {
            //kontrola sachu zo severovychodu
            if (sach.jeTamFigurka(this.getStlpec() - i, this.getRiadok() + i)) {
                pfSV++;
                if (pfSV > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() + i)) {
                break;
            }

            if (sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() + i) instanceof Strelec || sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() + i) instanceof Kralovna) {                    
                this.sachSV = true;
                System.out.println("Sach zo severovychodu");
                return true;
            }
        }
            
        
        
        //kontrola pohybu pre ohrozenie od kona
        int x = this.getRiadok();
        int y = this.getStlpec();
        int pozX[] = {x + 2, x + 1, x - 1, x - 2, x - 2, x - 1, x + 1, x + 2};
        int pozY[] = {y + 1, y + 2, y + 2, y + 1, y - 1, y - 2, y - 2, y - 1};
        
        for (int i = 0; i < 8; i++) {
            if (sach.jeTamFigurkaInejFarby(pozY[i], pozX[i])) {
                break;
            }
            
            if (sach.zistiFigurku(pozY[i], pozX[i]) instanceof Kon) {
                System.out.println("Sach konom");
                return true;
         
            }
        }
        
        if (this.getFarba() == 1) {
            if (sach.zistiFigurku(this.getStlpec() + 1, this.getRiadok() + 1) instanceof Pesiak && !sach.jeTamFigurkaInejFarby(this.getStlpec() + 1, this.getRiadok() + 1) || 
                sach.zistiFigurku(this.getStlpec() + 1, this.getRiadok() - 1) instanceof Pesiak && !sach.jeTamFigurkaInejFarby(this.getStlpec() + 1, this.getRiadok() - 1)    ) {
                System.out.println("Sach pesiakom");
                return true;
            }
        }
        
        if (this.getFarba() == 0) {
            if (sach.zistiFigurku(this.getStlpec() - 1, this.getRiadok() - 1) instanceof Pesiak && !sach.jeTamFigurkaInejFarby(this.getStlpec() - 1, this.getRiadok() - 1) || 
                sach.zistiFigurku(this.getStlpec() - 1, this.getRiadok() + 1) instanceof Pesiak && !sach.jeTamFigurkaInejFarby(this.getStlpec() - 1, this.getRiadok() + 1)    ) {
                System.out.println("Sach pesiakom");
                return true;
            }
        }
        return false;
              
    }
    
    
    
    
    
    @Override
    //kontroluje aby sa kral nepohol do sachu 
    public boolean jeCielovySach(Sach sach, int x, int y) {
        int pocitadloFiguriekNapravo = 0;
        int pocitadloFiguriekNalavo = 0;
        int pocitadloFiguriekNahor = 0;
        int pocitadloFiguriekNadol = 0;
        
        
        //kontrola sachu napravo od krala
        for (int i = x + 1; i < 8; i++) {  
            if (sach.jeTamFigurka(y, i)) {                
                pocitadloFiguriekNapravo++;
                if (y == this.getStlpec() && i == this.getRiadok()) {
                    pocitadloFiguriekNapravo--;
                }
                
                if (pocitadloFiguriekNapravo > 1) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(y, i) && sach.zistiFigurku(y, i) instanceof Veza || sach.jeTamFigurkaInejFarby(y, i) && sach.zistiFigurku(y, i) instanceof Kralovna) {
                
                System.out.println("Mal by si sach sprava");
                return true;
            }
           
        }
        
        
        //kontrola cieloveho sachu nalavo od krala
        for (int i = x - 1; i >= 0; i--) {    
            if (sach.jeTamFigurka(y, i)) {
                pocitadloFiguriekNalavo++;
                if (y == this.getStlpec() && i == this.getRiadok()) {
                    pocitadloFiguriekNalavo--;
                }
                if (pocitadloFiguriekNalavo > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(y, i) && sach.zistiFigurku(y, i) instanceof Veza || sach.jeTamFigurkaInejFarby(y, i) && sach.zistiFigurku(y, i) instanceof Kralovna) {
                System.out.println("Mal by si sach zlava");
                return true;
            }

        }
        
        
        //hladanie cieloveho sachu hore od krala
        for (int i = y - 1; i >= 0; i--) {   
            if (sach.jeTamFigurka(i, x)) {
                pocitadloFiguriekNahor++;
                if (i == this.getStlpec() && x == this.getRiadok()) {
                    pocitadloFiguriekNahor--;
                }
                if (pocitadloFiguriekNahor > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(i, x) && sach.zistiFigurku(i, x) instanceof Veza || sach.jeTamFigurkaInejFarby(i, x) && sach.zistiFigurku(i, x) instanceof Kralovna) {                
                
                System.out.println("Mal by si sach zhora");
                return true;
            } 

        }
        
        //hladanie cieloveho sachu nadol od krala
        for (int i = y + 1; i < 8; i++) {               
            if (sach.jeTamFigurka(i, x)) {
                pocitadloFiguriekNadol++;
                if (i == this.getStlpec() && x == this.getRiadok()) {
                    pocitadloFiguriekNadol--;
                }
                if (pocitadloFiguriekNadol > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(i, x) && sach.zistiFigurku(i, x) instanceof Veza || sach.jeTamFigurkaInejFarby(i, x) && sach.zistiFigurku(i, x) instanceof Kralovna) {                
                System.out.println("Mal by si sach zdola");
                return true;
                
            } 
        }
        
        
        //zistuje cielovu hrozbu konom 
        int pozX[] = {x + 2, x + 1, x - 1, x - 2, x - 2, x - 1, x + 1, x + 2};
        int pozY[] = {y + 1, y + 2, y + 2, y + 1, y - 1, y - 2, y - 2, y - 1};
        
        for (int i = 0; i < 8; i++) {
            if (sach.jeTamFigurkaInejFarby(pozY[i], pozX[i]) && sach.zistiFigurku(pozY[i], pozX[i]) instanceof Kon) {
                System.out.println("Mal by si sach konom");
                return true;
            }
        }
        
        
        int pfSZ = 0;
        int pfSV = 0;
        int pfJZ = 0;
        int pfJV = 0;
        //zistovanie od strelca
        
        for (int i = 1; i <= 8 ; i++) {
            if (sach.jeTamFigurka(y - i, x - i)) {
                pfSZ++;
                if (y - i == this.getStlpec() && x - i == this.getRiadok()) {
                    pfSZ--;
                }
                
                if (pfSZ > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(y - i, x - i) && sach.zistiFigurku(y - i, x - i) instanceof Strelec || sach.jeTamFigurkaInejFarby(y - i, x - i) && sach.zistiFigurku(y - i, x - i) instanceof Kralovna) {                        
                System.out.println("Mal by si sach zo severozapadu");
                return true;
            }
        }
        
        for (int i = 1; i <= 8 ; i++) {
            if (sach.jeTamFigurka(y + i, x - i)) {
                pfJZ++;
                if (y + i == this.getStlpec() && x - i == this.getRiadok()) {
                    pfJZ--;
                }
                
                if (pfJZ > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(y + i, x - i) && sach.zistiFigurku(y + i, x - i) instanceof Strelec || sach.jeTamFigurkaInejFarby(y + i, x - i) && sach.zistiFigurku(y + i, x - i) instanceof Kralovna) {                        
                System.out.println("Mal by si sach zo juhozapadu");
                return true;
            }
        }
        
        for (int i = 1; i <= 8 ; i++) {
            if (sach.jeTamFigurka(y + i, x + i)) {
                pfJV++;
                if (y + i == this.getStlpec() && x + i == this.getRiadok()) {
                    pfJV--;
                }
                
                if (pfJV > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(y + i, x + i) && sach.zistiFigurku(y + i, x + i) instanceof Strelec || sach.jeTamFigurkaInejFarby(y + i, x + i) && sach.zistiFigurku(y + i, x + i) instanceof Kralovna) {                        
                System.out.println("Mal by si sach zo juhovychodu");
                return true;
            }
        }
        
        for (int i = 1; i <= 8 ; i++) {
            if (sach.jeTamFigurka(y - i, x + i)) {
                pfSV++;
                if (y - i == this.getStlpec() && x + i == this.getRiadok()) {
                    pfSV--;
                }
                
                if (pfSV > 1) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(y - i, x + i) && sach.zistiFigurku(y - i, x + i) instanceof Strelec || sach.jeTamFigurkaInejFarby(y - i, x + i) && sach.zistiFigurku(y - i, x + i) instanceof Kralovna) {                        
                System.out.println("Mal by si sach zo severovychodu");
                return true;
            }
        }
        
         if (this.getFarba() == 1) {
            if (sach.zistiFigurku(y + 1, x + 1) instanceof Pesiak && sach.jeTamFigurkaInejFarby(y + 1, x + 1) || 
                sach.zistiFigurku(y + 1, x - 1) instanceof Pesiak && sach.jeTamFigurkaInejFarby(y + 1, x - 1)) {
                System.out.println("Mal by si sach pesiakom");
                return true;
            }
        }
        
        if (this.getFarba() == 0) {
            if (sach.zistiFigurku(y - 1, x - 1) instanceof Pesiak && sach.jeTamFigurkaInejFarby(y - 1, x - 1) || 
                sach.zistiFigurku(y - 1, x + 1) instanceof Pesiak && sach.jeTamFigurkaInejFarby(y - 1, x + 1)    ) {
                System.out.println("Mal by si sach pesiakom");
                return true;
            }
        }
        
        return false;
    }
    
  
    @Override
    //riesi, ci pohyb figurky zrusi sach, ak nie vrati false
    public boolean budeStaleSach(Sach sach) {          
        int pocitadloFiguriekNadol = 0; 
        int pocitadloFiguriekNahor = 0;
        int pocitadloFiguriekNapravo = 0;
        int pocitadloFiguriekNalavo = 0;
       
        if (this.sachZprava) {
            for (int i = this.getRiadok() + 1; i < 8; i++) {
                //zistuje pocet figuriek na svojej ceste
                if (sach.jeTamFigurka(this.getStlpec(), i)) {
                    pocitadloFiguriekNapravo++;
                    if (pocitadloFiguriekNapravo == 1 && !sach.jeTamFigurkaInejFarby(this.getStlpec(), i)) {
                        System.out.println("zabranil si sach sprava");
                        return false;
                    }
                }
            }
        }
        
        
         //hladanie sachu nalavo od krala
       
        if (this.sachZlava) {
            for (int i = this.getRiadok() - 1; i >= 0; i--) {                
                if (sach.jeTamFigurka(this.getStlpec(), i)) {
                    pocitadloFiguriekNalavo++;
                    if (pocitadloFiguriekNalavo == 1 && !sach.jeTamFigurkaInejFarby(this.getStlpec(), i)) {
                        System.out.println("zabranil si sachu zlava ");
                        return false;
                    }
                }
            }
        }
        
        if (this.sachZhora) {
            for (int i = this.getStlpec() - 1; i >= 0; i--) {
                
                if (sach.jeTamFigurka(i, getRiadok())) {
                    pocitadloFiguriekNahor++;

                    if (pocitadloFiguriekNahor == 1 && !sach.jeTamFigurkaInejFarby(i, this.getRiadok())) {
                        System.out.println("zabranil si sachu zhora ");
                        return false;
                    }
                }
            }
        }
        
        if (this.sachZdola) {
            for (int i = this.getStlpec() + 1; i <= 8; i++) { 
                if (sach.jeTamFigurka(i, this.getRiadok())) {
                    pocitadloFiguriekNadol++;
                    if (pocitadloFiguriekNadol == 1 && !sach.jeTamFigurkaInejFarby(i, this.getRiadok())) {
                        System.out.println("zabranil si sachu zdola ");
                        return false;
                    }
                }
            }
        }
        
        int pfSV = 0;
        int pfSZ = 0;
        int pfJV = 0;
        int pfJZ = 0;
        
        if (this.sachJZ) {
            for (int i = 1; i <= 8; i++) {
                if (sach.jeTamFigurka(this.getStlpec() + i, this.getRiadok() - i)) {
                    pfJZ++;
                    if (pfJZ == 1 && !sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() - i)) {
                        System.out.println("zabranil si sachu z juhozapad");
                        return false;
                    }
                }
            }
        }
        
        if (this.sachJV) {
            for (int i = 1; i <= 8; i++) {
                if (sach.jeTamFigurka(this.getStlpec() + i, this.getRiadok() + i)) {
                    pfJV++;
                    if (pfJV == 1 && !sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() + i)) {
                        System.out.println("zabranil si sachu z juhovychodu");
                        return false;
                    }
                }
            }
        }
        
        if (this.sachSZ) {
            for (int i = 1; i <= 8; i++) {
                if (sach.jeTamFigurka(this.getStlpec() - i, this.getRiadok() - i)) {
                    pfSZ++;
                    if (pfSZ == 1 && !sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() - i)) {
                        System.out.println("zabranil si sachu zo severozapadu");
                        return false;
                    }
                }
            }
        }
        
        if (this.sachSV) {
            for (int i = 1; i <= 8; i++) {
                if (sach.jeTamFigurka(this.getStlpec() - i, this.getRiadok() + i)) {
                    pfSV++;
                    if (pfSV == 1 && !sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() + i)) {
                        System.out.println("zabranil si sachu zo severovychodu");
                        return false;
                    }
                }
            }
        }
        

        
         
        return true;
              
    }
    
    //riesi ci sa figurka moze pohnut aby neposposobila sach 
    @Override
    public boolean mozeSaPohnut(Sach sach) {
        int pocitadloFiguriekNadol = 0; 
        int pocitadloFiguriekNahor = 0;
        int pocitadloFiguriekNapravo = 0;
        int pocitadloFiguriekNalavo = 0;
        
        for (int i = this.getRiadok() + 1; i < 8; i++) {  
            if (sach.jeTamFigurka(this.getStlpec(), i)) {
                pocitadloFiguriekNapravo++;
                
                if (pocitadloFiguriekNapravo > 1 || sach.getCieloveY() == this.getStlpec()) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(this.getStlpec(), i) && sach.zistiFigurku(this.getStlpec(), i) instanceof Veza || sach.jeTamFigurkaInejFarby(this.getStlpec(), i) && sach.zistiFigurku(this.getStlpec(), i) instanceof Kralovna) {
                System.out.println("mal by si sach sprava");
                return false;
            }
        }
        
        
        for (int i = this.getRiadok() - 1; i >= 0; i--) {    
            if (sach.jeTamFigurka(this.getStlpec(), i)) {
                pocitadloFiguriekNalavo++;
                if (pocitadloFiguriekNalavo > 1 || sach.getCieloveY() == this.getStlpec()) {
                    break;
                }
            }
            
            if (sach.jeTamFigurkaInejFarby(this.getStlpec(), i) && sach.zistiFigurku(this.getStlpec(), i) instanceof Veza || sach.jeTamFigurkaInejFarby(this.getStlpec(), i) && sach.zistiFigurku(this.getStlpec(), i) instanceof Kralovna) {                
                System.out.println("mal by si sach zlava");
                return false;
            }

        }
        
        for (int i = this.getStlpec() - 1; i >= 0; i--) {   
            if (sach.jeTamFigurka(i, this.getRiadok())) {
                pocitadloFiguriekNahor++;
                if (pocitadloFiguriekNahor > 1 || sach.getCieloveX() == this.getRiadok()) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(i, this.getRiadok()) && sach.zistiFigurku(i, this.getRiadok()) instanceof Veza || sach.jeTamFigurkaInejFarby(i, this.getRiadok()) && sach.zistiFigurku(i, this.getRiadok()) instanceof Kralovna) {                
                
                System.out.println("mal by si sach zhora");
                return false;
            } 

        }
        
        for (int i = this.getStlpec() + 1; i < 8; i++) {               
            if (sach.jeTamFigurka(i, this.getRiadok())) {
                pocitadloFiguriekNadol++;
                if (pocitadloFiguriekNadol > 1 || sach.getCieloveX() == this.getRiadok()) {
                    break;
                }
            }

            if (sach.jeTamFigurkaInejFarby(i, this.getRiadok()) && sach.zistiFigurku(i, this.getRiadok()) instanceof Veza || sach.jeTamFigurkaInejFarby(i, this.getRiadok()) && sach.zistiFigurku(i, this.getRiadok()) instanceof Kralovna) {                
                System.out.println("mal by si sach zdola");
                return false;
                
            } 
        }
        
        int pfSV = 0;
        int pfSZ = 0;
        int pfJV = 0;
        int pfJZ = 0;
        
        for (int i = 1; i <= 8; i++) {
            if (sach.jeTamFigurka(this.getStlpec() - i, this.getRiadok() + i)) {
                pfSV++;
                if (pfSV > 1 || sach.getCieloveX() - i == this.getRiadok() && sach.getCieloveY() + i == this.getStlpec()) {
                    break;
                } 
            }
            if (sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() + i) && sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() + i) instanceof Strelec || sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() + i) && sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() + i) instanceof Kralovna) {                
                System.out.println("mal by si sach zo SV");
                return false;
                
            } 
        }
        
        for (int i = 1; i <= 8; i++) {
            if (sach.jeTamFigurka(this.getStlpec() + i, this.getRiadok() + i)) {
                pfJV++;
                if (pfJV > 1 || sach.getCieloveX() - i == this.getRiadok() && sach.getCieloveY() - i == this.getStlpec()) {
                    break;
                } 
            }
            if (sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() + i) && sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() + i) instanceof Strelec || sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() + i) && sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() + i) instanceof Kralovna) {                
                System.out.println("mal by si sach z JV");
                return false;
                
            } 
        }
        
        for (int i = 1; i <= 8; i++) {
            if (sach.jeTamFigurka(this.getStlpec() - i, this.getRiadok() - i)) {
                pfSZ++;
                if (pfSZ > 1 || sach.getCieloveX() + i == this.getRiadok() && sach.getCieloveY() + i == this.getStlpec()) {
                    break;
                } 
            }
            if (sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() - i) && sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() - i) instanceof Strelec || sach.jeTamFigurkaInejFarby(this.getStlpec() - i, this.getRiadok() - i) && sach.zistiFigurku(this.getStlpec() - i, this.getRiadok() - i) instanceof Kralovna) {                
                System.out.println("mal by si sach z SZ");
                return false;
                
            } 
        }
        
        for (int i = 1; i <= 8; i++) {
            if (sach.jeTamFigurka(this.getStlpec() + i, this.getRiadok() - i)) {
                pfJZ++;
                if (pfJZ > 1 || sach.getCieloveX() + i == this.getRiadok() && sach.getCieloveY() - i == this.getStlpec()) {
                    break;
                } 
            }
            if (sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() - i) && sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() - i) instanceof Strelec || sach.jeTamFigurkaInejFarby(this.getStlpec() + i, this.getRiadok() - i) && sach.zistiFigurku(this.getStlpec() + i, this.getRiadok() - i) instanceof Kralovna) {                
                System.out.println("mal by si sach z JZ");
                return false;
                
            } 
        }
        
        return true;
    }
    
    
   
        
      
    
    
    
}
