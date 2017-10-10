package logika;

import java.util.*;

/*******************************************************************************
 * Instance třídy {@code Mistnost} představují ...
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class Batoh
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    private int kapacita = 4;
    
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private Map<String, Vec> seznamVeci;

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /**
     * Vytvoření batohu, do kterého se vkládají věci
     *
     */
    public Batoh()
    {
        this.kapacita = kapacita;
        seznamVeci = new HashMap<>();
        
    }
    //== NESOUKROMÉ METODY INSTANCÍ ====================================
    /**
     *  Metoda vypíše názvy věcí v batohu
     *
     *@return  názvy věcí
     */ 
    public String veciVBatohu() {
        String veci = "V batohu máš: ";
        for (String nazevVeci : seznamVeci.keySet()){
            veci += nazevVeci + " ";
        }
        return veci;
    }
    /**
     *  Metoda vloží věc do batohu
     *
     *@param  věc, kterou chceme vložit
     *
     *@return  true, pokud byla věc vložena a false, pokud nebyla vložena
     */     
    public boolean vlozVec (Vec vec)
    {
        if(seznamVeci.size() < kapacita)
        { 
            
            seznamVeci.put(vec.getNazev(), vec);
            
            if(seznamVeci.containsKey(vec.getNazev())){
                
                return true;
            }
            return false;
        }
        else
        {
            return false;
        }            
    }
    /**
     *  Metoda vyhodí věc z batohu
     *
     *@param  název věci, kterou chceme vyhodit
     *
     *@return  Odkaz na vyhozenou věc
     */ 
    public Vec vyhodVec (String nazevVeci)
    {
        return seznamVeci.remove(nazevVeci);            
    }
    /**
     *  Metoda zjistí, jestli je daná věc v batohu
     *
     *@param  název věci
     *
     *@return  true - věc je v batohu, false - věc není v batohu
     */ 
    public boolean obsahujeVec (String nazev){
        
        return seznamVeci.containsKey(nazev); 
    }
    /**
     *  Metoda vybere věc v batohu
     *
     *@param  název věci, kterou chceme vybrat
     *
     *@return  vybraná věc
     */ 
    public Vec vyberVec(String nazevVeci){
        
        return seznamVeci.get(nazevVeci);
    }
}
