package logika;

/**
 *  Třída PrikazPoloha implementuje pro hru příkaz poloha.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazPoloha implements IPrikaz {
    private static final String NAZEV = "poloha";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazPoloha(HerniPlan plan) {
        this.plan   = plan;
    }

    /**
     *  Provádí příkaz "jdi". Řekne hráči, v kterém prostoru se právě nachází.
     *
     *@param parametry - bez parametrů
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {

        return plan.getAktualniProstor().dlouhyPopis();
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
