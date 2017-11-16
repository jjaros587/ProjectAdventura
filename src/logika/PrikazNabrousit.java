package logika;

/**
 *  Třída PrikazNabrousit implementuje pro hru příkaz nabrousit.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazNabrousit implements IPrikaz {
    private static final String NAZEV = "nabrousit";
    private HerniPlan plan;
    private Batoh batoh;
    private Hrac hrac;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se vkládají věci
    */    
    public PrikazNabrousit(HerniPlan plan, Batoh batoh, Hrac hrac) {
        this.plan   = plan;
        this.batoh  = batoh;
        this.hrac  = hrac;
    }

    /**
     *  Provádí příkaz "nabrousit". Nabrousí věc, pokud ji má hráč u sebe,
     *  jde nabrousit a hráč je v kovárně, kde se dají brousit věci.
     *
     *@param parametry - věc, která se má nabrousit
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        
        if (parametry.length == 0) {

            return "Nevím, co mám nabrousit! Tvar příkazu: nabrousit [věc]\n";
        }
        
        String coNabrousit = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();
        

        if (aktualniProstor.getNazev().equals("kovarna")) {
            
            if (batoh.obsahujeVec(coNabrousit)){
            
                if (coNabrousit.equals("mec")){
                    
                    Vec zbran = batoh.vyberVec(coNabrousit);
                    
                    if(!zbran.getNabrouseno()){
                        
                        zbran.setUtok(zbran.getUtok() + 1);
                        zbran.setNabrouseno(true);
                        hrac.setUtok(hrac.getUtok() + 1);
                    
                        return "Nabrousil sis meč\n";
                    }
                    else{
                        return "Tuto věc nemůžeš nabrousit\n";
                    }      
                }
                else {
                    
                    return "Tuto věc nemůžeš nabrousit\n";
                }
            }
            else {
                
                return coNabrousit +" nemáš v batohu\n";
            }
        }
        else {
            
            return "Brousit můžeš pouze v kovárně\n";
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
