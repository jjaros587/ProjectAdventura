package logika;
import java.util.*;
import utils.Observer;
import utils.Subject;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2015/2016
 */
public class HerniPlan implements Subject{
    
    private Prostor aktualniProstor;   
    private Vec klic;    
    private Map<String, Prostor> prostory;   
    private boolean prohra = false; 
    
    private List<Observer> listObserveru = new ArrayList<Observer>();

     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        this.prohra = prohra;
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        
        //== PROSTORY ======================================================
        prostory = new HashMap<>();
        this.prohra = prohra;
        Prostor chaloupka   = new Prostor("chaloupka","Chaloupka, ve které si se narodil", 150, 450);
        Prostor rozcesti    = new Prostor("rozcesti", "Rozcestí, které vede na mnoho míst", 150, 350);
        Prostor les         = new Prostor("les","Les, kde se ukrývá medvěd. Pokud ho zabiješ, získáš peníze, za které si můžeš doplnit životy", 150, 250);
        Prostor prikop      = new Prostor("prikop","Příkop, ve kterém je krokodýl, který hlídá vstup do hradu", 150, 150);
        Prostor hrad        = new Prostor("hrad","Opuštěný hrad", 0, 0);
        Prostor zbrojnice   = new Prostor("zbrojnice","Zbrojnice, ve které můžeš nalézt užitečné předměty", 50, 0);
        Prostor kovarna     = new Prostor("kovarna","Kovárna, kde si můžeš nabrousit meč", 250, 0);
        Prostor hospoda     = new Prostor("hospoda", "Hospoda, kde si můžeš dát pivo a doplnit si tak životy", 0, 500);
        Prostor doupe       = new Prostor("doupe","Doupě, ve kterém žije trpaslík", 0, 350);
        Prostor skrys       = new Prostor("skrys","Skrýš s dědictvím", 0, 200);
        Prostor jeskyne     = new Prostor("jeskyne","Jeskyně, kde přebívá drak", 300, 350);
        Prostor pokladnice  = new Prostor("pokladnice","Pokladnice, kde je ukryt diamant", 300, 250);
        
            prostory.put(chaloupka.getNazev(), chaloupka);
            prostory.put(rozcesti.getNazev(), rozcesti);
            prostory.put(les.getNazev(), les);
            prostory.put(prikop.getNazev(), prikop);
            prostory.put(hrad.getNazev(), hrad);
            prostory.put(zbrojnice.getNazev(), zbrojnice);
            prostory.put(kovarna.getNazev(), kovarna);
            prostory.put(hospoda.getNazev(), hospoda);
            prostory.put(doupe.getNazev(), doupe);
            prostory.put(skrys.getNazev(), skrys);
            prostory.put(jeskyne.getNazev(), jeskyne);
            prostory.put(pokladnice.getNazev(), pokladnice);
        
            skrys.setZamceno(true);
            skrys.setLzeOdemknoutHracem(true);
            hrad.setZamceno(true);
            pokladnice.setZamceno(true);
            
            jeskyne.setViditelna(false);
            jeskyne.setZamceno(true);
            
            doupe.setViditelna(false);
             
            

            aktualniProstor = chaloupka;
         
        //== VÝCHODY ======================================================
        chaloupka.setVychod(rozcesti);
        
        rozcesti.setVychod(hospoda);
        rozcesti.setVychod(les);
        rozcesti.setVychod(chaloupka);   
        rozcesti.setVychod(doupe); 
          
        les.setVychod(rozcesti);
        les.setVychod(prikop);
        
        hospoda.setVychod(rozcesti);
        
        prikop.setVychod(les);
        prikop.setVychod(hrad);
        
        hrad.setVychod(prikop);
        hrad.setVychod(zbrojnice);
        hrad.setVychod(kovarna);
        
            zbrojnice.setVychod(hrad);
            kovarna.setVychod(hrad);
             
        doupe.setVychod(skrys);
        doupe.setVychod(rozcesti);
        
        
        jeskyne.setVychod(rozcesti);
        jeskyne.setVychod(pokladnice);
        
        pokladnice.setVychod(jeskyne);

        
        //== POSTAVY ======================================================
        Postava trpaslik    = new Postava("trpaslik", "Trpaslík vlastní klíč od skrýše s pokladem, který ti předá, pokud splníš jeho úkol", false);
            trpaslik.setLzeDat(true);
        Postava drak        = new Postava("drak", "Pokud zabiješ draka, otevřou se dveře do pokladnice", true, true, 8, 3, 0);
        Postava medved      = new Postava("medved", "Pokud zabiješ medvěda, dostaneš 2 zlaťáky", true, true, 2, 2, 1);
        Postava krokodyl    = new Postava("krokodyl", "Pokud zabiješ krokodýla, můžeš vstoupit do hradu a dostaneš 2 zlaťáky",true, true, 2, 1, 1);
        
        
            //-- vložení postav ----------------------------
            doupe.vlozPostavu(trpaslik);
            jeskyne.vlozPostavu(drak);
            les.vlozPostavu(medved);
            prikop.vlozPostavu(krokodyl);

        //== VĚCI ======================================================
        Vec stit           = new Vec("stit", true, true, 1);
        Vec prilba         = new Vec("prilba", true, true, 1);
        Vec lano           = new Vec("lano", true, false);
        Vec diamant        = new Vec("diamant", true, false);
        Vec truhla         = new Vec("truhla", false, false);
            truhla.setLzeProzkoumat(true);
            truhla.vlozVec(diamant);
        klic               = new Vec("klic", true, false);
            skrys.setKlic(klic);

            //-- vložení věcí ----------------------------
            zbrojnice.vlozVec(stit);
            zbrojnice.vlozVec(prilba);
            zbrojnice.vlozVec(lano);
            pokladnice.vlozVec(truhla);       
    }
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        
        return aktualniProstor;
    }
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Vec getKlic(){
        
        return klic;
    }   
    /**
     *  Metoda slouží k vložení prostoru do seznamu prostorů
     *
     *@param     vkládaný prostor
     */
    public void vlozProstor(Prostor prostor){
        
        prostory.put(prostor.getNazev(), prostor);
    }
    /**
     *  Metoda vrací odkaz na vybíraný prostor
     *
     *@return     vybraný prostor
     *
     *@param     název prostoru, který chceme vybrat
     */
    public Prostor vyberProstor(String nazevProstoru){
        
        return prostory.get(nazevProstoru);
    }
    /**
     *  Metoda slouží ke zjištění výhry
     *
     *@return     true - splněny podmínky výhry, false - nesplněny podmínky výhry
     */
    public boolean vyhra(){
        
        if(aktualniProstor.getNazev().equals("skrýš")){
            
            return true;
        }
            return false;
    }
    /**
     *  Metoda slouží ke zjištění prohry
     *
     *@return     true - splněny podmínky prohry, false - nesplněny podmínky prohry
     */
    public boolean prohra(Hrac hrac){
        
         if(hrac.getZivoty() <= 0){
            
            return true;
        }
            return false;
    }
    /**
     * Registrace observeru
     * @param observer Observer
     */
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }
    /**
     * Zrušení observeru
     * @param observer Observer
     */
    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }
    /**
     * Oznámení observeru
     */   
    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
