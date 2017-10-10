package logika;

import java.util.*;

/*******************************************************************************
 * Instance třídy Vec představují věci v místnostech nebo v batohu.
 * 
 *
 * @author    Jakub Jaroš
 * @version   pro školní rok 2015/2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean sebratelna;
    private boolean jeZbran;
    private boolean lzeProzkoumat = false;
    private Map<String, Vec> seznamVeci;
    private int utok;

    /***************************************************************************
     *  Pomocí konstruktoru se vytváří instance popisující věc.
     *  
     *  @param  nazev       název věci
     *  @param  sebratelna  zda lze věc z prostoru sebrat
     */
    public Vec(String nazev, boolean sebratelna, boolean jeZbran) {
        this.nazev          = nazev;
        this.sebratelna     = sebratelna;
        this.jeZbran        = jeZbran;
        this.jeZbran        = jeZbran;
        this.lzeProzkoumat  = lzeProzkoumat;
        seznamVeci          = new HashMap<>();
    }
    /***************************************************************************
     *  Pomocí konstruktoru se vytváří instance popisující věc.
     *  
     *  @param  nazev       název věci
     *  @param  sebratelna  zda lze věc z prostoru sebrat
     *  @param  utok        útok věci
     */
    public Vec(String nazev, boolean sebratelna, boolean jeZbran, int utok) {
        this.nazev          = nazev;
        this.sebratelna     = sebratelna;
        this.jeZbran        = jeZbran;
        this.lzeProzkoumat  = lzeProzkoumat;
        this.utok           = utok;
        seznamVeci          = new HashMap<>();
    }
    /**
     * Metoda equals pro porovnání dvou věcí. Překrývá se metoda equals ze
     * třídy Object. Dvě věci jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování vyhledávání věcí v batohu.
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object obj) {
      
        if (this == obj) {
            return true;
        }
        if (obj instanceof Vec) {
            
            Vec druha = (Vec) obj;
            
            if(this.nazev == druha.nazev){
               
                return true;
                
            }    
            return false;
        }
        else {
            
            return false;
        }  
    }
    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 5;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
    
    //==== Veřejné metody =====================
    /**
     * metoda vrací název věci
     * 
     * @return   název věci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * metoda vrátí true, pokud lze věc v místnosti sebrat
     * 
     * @return    true - lze věc sebrat, false - věc nejde sebrat
     */
    public boolean isSebratelna() {
        return sebratelna;
    }
    public boolean jeZbran() {
        return jeZbran;
    }
    /**
     * metoda získá hodnotu útoku věci
     * 
     * @return    hodnota útoku
     */
    public int getUtok(){
        return utok;
    }
    /**
     * metoda nastaví hodnotu útoku věci
     * 
     * @param    nová hodnota útoku
     */
    public void setUtok(int utok){
        this.utok = utok;
    }
    /**
     * metoda vrací, jestli je věc prozkoumána nebo ne
     * 
     * @return   true - věc lze prozkoumat, false - věc nelze prozkoumat
     */
    public boolean getLzeProzkoumat() {
        return lzeProzkoumat;
    }
    
    /**
     * metoda nastaví, jestli lze věc prozkoumat nebo ne
     * 
     * @param    true - věc lze prozkoumat, false - věc nelze prozkoumat
     */
    public void setLzeProzkoumat(boolean lzeProzkoumat) {
        this.lzeProzkoumat = lzeProzkoumat;
    }
    /**
     *  Metoda vloží věc do seznamu věcí ve věci
     *
     *@param  věc, kterou chceme vložit
     */
    public void vlozVec(Vec vec){   
        seznamVeci.put(vec.getNazev(), vec);
    }
    /**
     * metoda vrací, seznam věcí ve věci
     * 
     * @return   seznam věcí
     */
    public Map<String, Vec> vratSeznamVeci(){
        return seznamVeci;
    }
}
