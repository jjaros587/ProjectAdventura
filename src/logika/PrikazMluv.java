package logika;

/**
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    private Hrac hrac;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param hráč
    */    
    public PrikazMluv(HerniPlan plan) {
        this.plan   = plan;
        this.hrac   = hrac;
    }

    /**
     *  Provádí příkaz "mluv". Promluví s postavou, pokud s ní lze mluvit.
     *
     *@param parametry - na koho mluvit
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
    
        if (parametry.length == 0) {
            return "Nevím s ký mám mluvit! Tvar příkazu: mluv [postava]\n";
        }
        
        String jmenoPostavy = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (!aktualniProstor.obsahujePostavu(jmenoPostavy)) {
            
            return "Taková postava se v prostoru nenachází\n";
        }
        if (!jmenoPostavy.equals("trpaslík")){
            
            return "S touto postavou nemůžeš mluvit\n";
        }
        
        plan.vyberProstor("rozcestí").setVychod(plan.vyberProstor("jeskyně"));
        plan.vyberProstor("jeskyně").setViditelna(true);
        plan.vyberProstor("jeskyně").setZamceno(false);
        
        return     "JÁ: Poslal mě za tebou můj otec. Prý u tebe schoval poklad.\n\n"
                 + "TRPASLÍK: Ano, schoval. Ale klíč od skrýše ti dám pouze, když mi přineseš diamant, který hlídá drak\n\n"
                 + "K drakovi se dostaš z rozcestí\n\n"
                 + "JÁ: Provedu\n";
        
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
