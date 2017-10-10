package logika;

/**
 *  Třída PrikazVyhod implementuje pro hru příkaz vyhod.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazVyhod implements IPrikaz {
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;
    private Batoh batoh;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se vkládají věci
    */    
    public PrikazVyhod(HerniPlan plan, Batoh batoh) {
        this.plan   = plan;
        this.batoh  = batoh;
    }

    /**
     *  Provádí příkaz "vyhod". Zkouší vyhodit věc z Batohu.
     *
     *@param parametry - věc, která se má vyhodit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {

            return "Nevím com mám vyhodit! Tvar příkazu: vyhod [věc]\n";
        }

        String coVyhodit = parametry[0];

        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vyhodit             = batoh.vyhodVec(coVyhodit);


        if (coVyhodit == null) {
            
            return "Takovou věc v batohu nemáš!\n";
        }
        else {
            
            aktualniProstor.vlozVec(vyhodit);
                
            return "Vyhodil si z batohu "+ coVyhodit +". Pokud budeš vět potřebovat najdeš jí v "
                    + aktualniProstor.getNazev() + ", kde jsi ji zanechal.\n";
            
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
