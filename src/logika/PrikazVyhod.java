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
    private Hrac hrac;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se vkládají věci
    */    
    public PrikazVyhod(HerniPlan plan, Batoh batoh, Hrac hrac) {
        this.plan   = plan;
        this.batoh  = batoh;
        this.hrac   = hrac;
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
        Vec vyhodit = batoh.vyberVec(coVyhodit);


        if (coVyhodit == null) {
            
            return "Takovou věc v batohu nemáš!\n";
        }
        else {
            
            String odpoved = "Vyhodil si z batohu "+ coVyhodit +". Pokud budeš vět potřebovat najdeš jí v "
                            + aktualniProstor.getNazev() + ", kde jsi ji zanechal.\n";
            
            aktualniProstor.vlozVec(vyhodit);
            
            if(vyhodit.jeZbran()){
                
                int utok    = hrac.getUtok();
                utok       -= vyhodit.getUtok();
                
                hrac.setUtok(utok);
                
                odpoved += "\nTvůj útok je nyní: " + utok;
            }
            batoh.vyhodVec(coVyhodit);
            return odpoved;
            
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
