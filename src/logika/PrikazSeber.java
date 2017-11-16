package logika;

/*******************************************************************************
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jakub Jaroš
 * @version   pro školní rok 2015/2016
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;
    private Hrac hrac;

    //== Konstruktory a tovární metody =============================================
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se ukládají věci
    */ 
    public PrikazSeber (HerniPlan plan, Batoh batoh, Hrac hrac) {
        this.plan = plan;
        this.batoh = batoh;
        this.hrac = hrac;
    }

    //== Nesoukromé metody======================================

    /**
     *  Provádí příkaz "seber". Nejdřív hledá sbíranou věc v prostoru. Pokud se věc v prostoru nachází, tak jí
     *  sebere a vloží do batohu (pokud je přenositelná a v batohu je místo).
     *
     *@param parametry - název sbírané věci
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {  
        
        if(parametry.length == 0) {
            
            return "Nevím co mám sebrat! Tvar příkazu: seber [věc]\n";
        } 
        
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.odeberVec(nazevVeci);
        
        if (sbirana == null){
            
            return "V místnosti se taková věc nenachází!!!\n";
        }

        if (!sbirana.isSebratelna()) {  
            
            aktualniProstor.vlozVec(sbirana);  
            return "Tato věc nelze sebrat.\n";
        }
        if (batoh.vlozVec(sbirana)) {
            
            String odpoved = "Sebral jsi věc " + nazevVeci + 
                             " a vložil sis tuto věc do batohu.\n";
            
            if(sbirana.jeZbran()){
                
                int utok    = hrac.getUtok();
                utok       += sbirana.getUtok();
                
                hrac.setUtok(utok);
                
                odpoved += "Tvůj útok: "+utok;
            }
            
            return odpoved;
        }
        else {
            
            aktualniProstor.vlozVec(sbirana);
            return "Věc nemůžeš sebrat, máš plný batoh. Můžeš z batohu něco vyhodit.\n";
        }
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
