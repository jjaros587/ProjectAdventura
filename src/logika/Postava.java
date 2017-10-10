package logika;

import java.util.*;



/*******************************************************************************
 * Instance třídy {@code Postava} představují ...
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class Postava
{
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private String jmeno;
    private String popis;
    private boolean jdeZabit;
    private boolean zije;
    private int pocetZivotu;
    private int utok;
    private int odmena;

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /**
     * Vytvoření postavy se zadaným jménem, popisem a možností zabití
     *
     * @param jmeno     - jednoznačné jméno postavy
     * @param popis     - Popis postavy.
     * @param jdeZabit  - udává, jestli jde postava zabít nebo ne
     */
    public Postava(String jmeno, String popis, boolean jdeZabit)
    {
        this.jmeno      = jmeno;
        this.popis      = popis;
        this.jdeZabit   = jdeZabit;
    }
    /**
     * Vytvoření postavy se zadaným jménem, popisem a možností zabití
     *
     * @param jmeno         - jednoznačné jméno postavy
     * @param popis         - Popis postavy.
     * @param jdeZabit      - udává, jestli jde postava zabít nebo ne
     * @param zije          - udává, jestli postava žije, nebo ne
     * @param pocetZivotu   - počet životů postavy
     * @param utok          - útok postavy
     * @param odmena        - udává, kolik hráč dostana za zabití postavy
     */
    public Postava(String jmeno, String popis, boolean jdeZabit, boolean zije, int pocetZivotu, int utok, int odmena)
    {
        this.jmeno          = jmeno;
        this.popis          = popis;
        this.jdeZabit       = jdeZabit;
        this.zije           = zije;
        this.pocetZivotu    = pocetZivotu;
        this.utok           = utok;
        this.odmena         = odmena;
    }
    /**
     * Metoda equals pro porovnání dvou postav. Překrývá se metoda equals ze
     * třídy Object. Dvě postavy jsou shodné, pokud mají stejné jméno. Tato
     * metoda je důležitá z hlediska správného fungování vyhledávání postav v místnosti.
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object obj) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == obj) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (obj instanceof Postava) {
            
            Postava druha = (Postava) obj;


            return (java.util.Objects.equals(this.jmeno, druha.jmeno));
            
        }
        return false;    
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
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.jmeno);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
    /**
     *  Metoda vypíše jméno postavy
     *
     *@return  jméno postavy
     */ 
    public String getJmeno() {
        return jmeno;       
    }
    /**
     *  Metoda vypíše názvy věcí v batohu
     *
     *@return  true - postavu lze zabít, false - postavu nelze zabít
     */ 
    public boolean jdeZabit(){
        return jdeZabit;
    }
    /**
     *  Metoda zjistí, jestli postava žije
     *
     *@return  true - postava žije, false - postava nežije
     */ 
    public boolean getZije(){
        return zije;
    }
    /**
     *  Metoda nastaví, jestli postava žije nebo ne
     *
     *@param  true - postava žije, false - postava nežije
     */ 
    public void setZije(boolean zije){
        this.zije = zije;
    }
    /**
     *  Metoda vrátí počet životů postavy
     *
     *@return  počet životů
     */ 
    public int getPocetZivotu(){
        return pocetZivotu;
    } 
    /**
     *  Metoda vrátí názvy věcí v batohu
     *
     *@return  útok
     */ 
    public int getUtok(){
        return utok;
    } 
    /**
     *  Metoda vrátí počet zlaťáků, které hráč dostane za zabití postavy
     *
     *@return  odměna
     */ 
    public int getOdmena(){
        return odmena;
    } 
    /**
     *  Metoda vrátí popis postavy
     *
     *@return  popis
     */ 
    public String getPopis(){
        return popis;
    }
}
