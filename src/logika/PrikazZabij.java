package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 *  Třída PrikazZabij implementuje pro hru příkaz zabij.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazZabij implements IPrikaz {
    private static final String NAZEV = "zabij";
    private HerniPlan plan;
    private Batoh batoh;
    private Hrac hrac;
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se vkládají věci
    *  @param hráč
    */    
    public PrikazZabij(HerniPlan plan, Batoh batoh, Hrac hrac, Hra hra) {
        this.plan   = plan;
        this.batoh  = batoh;
        this.hrac   = hrac;
        this.hra    = hra;
    }

    /**
     *  Provádí příkaz "zabij". Zaútočí na postavu, pokud je daná věc v místnosti a lze ji zabít.
     *
     *@param parametry - postavu, na kterou se má zaútočit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        
        if (parametry.length == 0) {

            return "Nevím, koho mám zabít! Tvar příkazu: zabij [postava]\n";
        }

        String kohoZabit = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();

        Postava postava = aktualniProstor.vyberPostavu(kohoZabit);
        
        if(postava == null) {
            updateHerniPlan();
            return "Taková postava se v prostoru nenachází\n";
        } 
            
        if (!postava.jdeZabit()) {
            updateHerniPlan();
            return "Nemůžeš zabít " + kohoZabit+"\n";
        }
        
        if(!postava.getZije()){
            updateHerniPlan();
            return "Dvakrát stejnou postavu nezabiješ\n";
        }
        String odpoved = null;
        
        int utok    = hrac.getUtok();
        int zivoty  = hrac.getZivoty();
        
        int utokPostavy     = postava.getUtok();
        int zivotyPostavy   = postava.getPocetZivotu();

        while(zivotyPostavy > 0 || zivoty > 0){
            
            zivotyPostavy -= utok;
            
            if(zivotyPostavy <=0){
                
                
                hrac.setZivoty(zivoty);
                hrac.setPenize(hrac.getPenize() + postava.getOdmena());
                postava.setZije(false);
                
                odpoved = "Zabil jsi postavu " + kohoZabit;
                
                if(kohoZabit.equals("krokodyl")){
                    plan.vyberProstor("hrad").setZamceno(false);
                    
                    odpoved += ". Cesta do hradu je nyní volná.\n";
                }
                
                if(kohoZabit.equals("drak")){
                    plan.vyberProstor("pokladnice").setZamceno(false);
                    batoh.vlozVec(new Vec("draci_sklo", true, false));
                    
                    odpoved += ". Nyní můžeš vstoupit do pokladnice.\nNavíc si získal dračí sklo\n";
                }
                updateHerniPlan();
                return "Tvoje životy: "+ zivoty+"\n"
                        +odpoved+"\n";

            }

            zivoty -= utokPostavy;
            
            if(zivoty <=0){
                
                hrac.setZivoty(zivoty);
                updateHerniPlan();
                return "Byl si zabit\n";

            }
        }
        updateHerniPlan();
        return "";
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
