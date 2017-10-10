package logika;

/**
 *  Třída PrikazLano implementuje pro hru příkaz lano.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazLano implements IPrikaz {
    private static final String NAZEV = "lano";
    private HerniPlan plan;
    private Batoh batoh;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se ukládají věci
    */    
    public PrikazLano(HerniPlan plan, Batoh batoh) {
        this.plan   = plan;
        this.batoh  = batoh;
    }

    /**
     *  Provádí příkaz "lano". Zkouší natáhnout lano do zadaného prostoru. Pokud do prostoru
     *  lze natáhnout lano a má hráč lano v batohu, lano se natáhne.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         ke kterému se má natáhnout lano.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {

            return "Nevím kam mám natáhnout lano! Tvar příkazu: lano [prostor]\n";
        }

        String smer = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        if(!batoh.obsahujeVec("lano")){
            
            return "Nejdřív musíš sebrat lano\n";   
        }

        if (smer.equals("doupě")) {
            
            aktualniProstor.setVychod(plan.vyberProstor("doupě"));
            
            batoh.vyhodVec("lano");
            
            return "Natáhl jsi lano k doupěti trpaslíka. Nyní můžeš projít\n";
        }
        else {
            
            return "Tam nemůžeš natáhnout lano!\n";
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

}
