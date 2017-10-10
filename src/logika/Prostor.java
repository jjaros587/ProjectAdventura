package logika;

import java.util.*;
import java.util.stream.*;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jakub Jaroš
 * @version pro školní rok 2015/2016 LS
 */
public class Prostor {
    
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private String nazev;
    private String popis;
    private boolean zamceno = false;
    private boolean viditelna = true;
    private Vec klic;
    private Set<Prostor> vychody;
    private Map<String, Vec> veci;
    private Map<String, Postava> postavy;

    
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param zamceno -  Udává, jestli je prostor zamčen nebo ne.
     */
    public Prostor(String nazev, String popis) {
        this.nazev      = nazev;
        this.popis      = popis;
        this.zamceno    = zamceno;
        this.viditelna  = viditelna;
        vychody         = new HashSet<>();
        veci            = new HashMap<>();
        postavy         = new HashMap<>();
    }
    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
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
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v prostoru " + popis + "\n\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisPostav() + "\n";
    } 

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String popisVychodu() {
        
        String vracenyText = "vychody:";
        
        for (Prostor sousedni : vychody) {
            
            vracenyText += " " + sousedni.getNazev();
            
            if (sousedni.jeZamceno()) {
                
                 vracenyText += "(zamknuto)";
            }
        }
        
        return vracenyText;
    }
    /**
     * Vrací textový řetězec, který popisuje věci v prostoru, například:
     * "věci: lano ".
     *
     * @return Popis věcí - názvů věcí v prostoru
     */
    public String popisVeci(){
        
        String vracenyText = "veci:";
        
        for (String nazev : veci.keySet()) {
            
            vracenyText += " " + nazev;
            
           
        }
        return vracenyText;
    }
    /**
     * Vrací textový řetězec, který popisuje postavy v prostoru, například:
     * "postavy: drak ".
     *
     * @return Popis postav - jména postav v prostoru
     */
    public String popisPostav(){
        
        String vracenyText = "postavy:";
        
        for (String jmeno : postavy.keySet()) {
            
            vracenyText += " " + jmeno;
            
           
        }
        return vracenyText;
    }
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        
        List<Prostor>hledaneProstory = 
        
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
                   
        if(hledaneProstory.isEmpty()){
            
            return null;
        }
        else {
            
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        
        return Collections.unmodifiableCollection(vychody);
    }
    /**
     *  Metoda nastaví východ z prostoru
     *
     *@param  prostor, kam chceme nastavit východ
     */
    public void setVychod(Prostor vedlejsi) {
        
        vychody.add(vedlejsi);
    }
    /**
     *  Metoda nastaví viditelnost prostoru
     *
     *@param  true - místnost je viditelná, false - místnost je skryta
     */
    public void setViditelna(boolean viditelna){
        
        this.viditelna = viditelna;
    }
    /**
     *  Metoda vrací viditelnosto místnosti
     *
     *@return  true - místnost je viditelná, false - místnost je skryta
     */
    public boolean isViditelna(){
        
        return viditelna;
    }
    
    //== KLÍČ =========================================
    /**
     *  Metoda zamkne nebo odemkne prostor
     *
     *@param  true - zamknuto, false - odemčeno
     */
    public void setZamceno(boolean zamceno){
        
        this.zamceno = zamceno;
    }
    /**
     * Vrací hodnotu true, pokud je místnost zamčena nebo false, pokud je odemčena
     *
     * @return Viditelnost místnosti
     */
    public boolean jeZamceno(){
        return zamceno;
    }
    /**
     *  Metoda nastaví klíč k odemčení místnosti
     *
     *@param  věc, kterou lze odemknout místnost
     */
    public void setKlic(Vec klic) {
        this.klic = klic;
    }
    /**
     *  Metoda vrací klíč k prostoru
     *
     *@return  klíč, kterým jde odemknout prostor
     */
    public Vec getKlic() {
        return klic;
    }

    //== VĚCI ========================================= 
    /**
     *  Metoda vloží věc do seznamu věcí v prostoru
     *
     *@param  věc, kterou chceme vložit
     */
    public void vlozVec (Vec vec){
        
        veci.put(vec.getNazev(), vec);
    }
    /**
     *  Metoda odebere věc ze seznamu věcí v prostoru
     *
     *@param  název věci, kterou chceme odebrat
     *
     *@return  odebíraná věc
     */
    public Vec odeberVec(String nazevVeci){
        
       return veci.remove(nazevVeci);
        
    }
    /**
     *  Metoda vrací true, pokud je věc v místnosti a false, pokud není
     *
     *@param  název věci, u které chceme zjistit, jestli se nachází v prostoru
     *
     *@return  true, pokud je věc v místnosti a false, pokud věc není v místnosti
     */
    public boolean obsahujeVec (String nazevVeci){
        
        return veci.containsKey(nazevVeci); 
    }
    /**
     *  Metoda vybere věc v místnosti
     *
     *@param  název věci, kterou chceme vybrat
     *
     *@return  vybraná věc
     */
    public Vec vyberVec(String nazevVeci){
        
        return veci.get(nazevVeci);
    }
    
    //== Postavy ========================================= 
    /**
     *  Metoda vloží postavu do seznamu postav v prostoru
     *
     *@param  postava, kterou chceme vložit
     */
    public void vlozPostavu(Postava postava){
        
        postavy.put(postava.getJmeno(), postava);
    }
    /**
     *  Metoda odebere postavu ze seznamu postav v prostoru
     *
     *@param  jméno postavy, kterou chceme odebrat
     *
     *@return  odebíraná postava
     */    
    public Postava odeberPostavu(String jmenoPostavy){
        
       return postavy.remove(jmenoPostavy);    
    }
    /**
     *  Metoda zjistí, jestli postava nachází v prostoru
     *
     *@param  jméno postavy, u které chceme zjistit, jestli se nachází v prostoru
     *
     *@return  true, pokud je postava v prostoru, nebo false, pokud není v prostoru
     */
    public boolean obsahujePostavu (String jmenoPostavy){
        
        return postavy.containsKey(jmenoPostavy);
    } 
    /**
     *  Metoda vybere postavu v místnosti
     *
     *@param  jméno postavy, kterou chceme vybrat
     *
     *@return  vybraná postava
     */
    public Postava vyberPostavu(String jmenoPostavy){
        
        return postavy.get(jmenoPostavy);
    }
}
