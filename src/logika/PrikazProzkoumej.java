package logika;
import java.util.*;
/**
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazProzkoumej(HerniPlan plan) {
        this.plan   = plan;
    }

    /**
     *  Provádí příkaz "prozkoumej". Pokud věc jde prozkoumat, tzn. pokud ještě nebyla prozkoumána
     *  prozkoumá danou věc.
     *
     *@param parametry - věc, kterou prozkoumat
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        
        if (parametry.length == 0) {

            return "Nevím, co mám prozkoumat! Tvar příkazu: prozkoumej [věc]\n";
        }
    
        String coProzkoumat = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();                

        if (!aktualniProstor.obsahujeVec(coProzkoumat)) {
            
            return coProzkoumat + " se v " + aktualniProstor.getNazev() + " nevyskytuje\n";
        }
        
        Vec vec = aktualniProstor.vyberVec(coProzkoumat);
        
        if(!vec.getLzeProzkoumat()){
            
            return coProzkoumat + " nemůžeš prozkoumat!\n";
        }
        
        Map<String, Vec> veciSeznam = vec.vratSeznamVeci();
        
        if(veciSeznam.isEmpty()){
            
            return "V " + coProzkoumat + " nic není!\n";
        }

        String odpoved = "Prozkoumal jsi "+coProzkoumat+" a našel jsi tyto věci:";
        
        for (String nazev : veciSeznam.keySet()) {
            
            aktualniProstor.vlozVec(veciSeznam.get(nazev));
            
            odpoved += " " + nazev+"\n";
       
        }
        odpoved += "Nyní je můžeš sebrat.\n";
        
        vec.vratSeznamVeci().clear();
        updateHerniPlan();
        return odpoved;
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    @Override
    public void updateHerniPlan() {
        plan.notifyObservers();
    }

}
