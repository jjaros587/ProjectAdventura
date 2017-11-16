package logika;

/*******************************************************************************
 * Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jakub Jaroš
 * @version   pro školní rok 2015/2016
 */
public class PrikazOdemkni implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;
    private Batoh batoh;

    //== Konstruktory a tovární metody =============================================
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se vkládají věci
    */    
    public PrikazOdemkni (HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody======================================

    /**
     *  Provádí příkaz "odemkni". Testuje, jestli má hráč v batohu věc,
     *  kterou lze odemknout prostor a pokud ano, tak ji odemkne.
     *
     *@param parametry - místnost, která se má odemknout
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {  
        
        if(parametry.length == 0) {
            
            return "Nevím co mám odemknout! Tvar příkazu: odemkni [prostor]\n";
        } 
        
        String odemknout = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Prostor sousedniProstor = aktualniProstor.vratSousedniProstor(odemknout);
        
        String nazevKlice = sousedniProstor.getKlic().getNazev();
        
        if (sousedniProstor == null){
            
            return "Tam se odsud jít nedá!!!\n";
        }

        if (!sousedniProstor.jeZamceno()) {  
             
            return "Místnost již je odemčena.\n";
        }
        if(!sousedniProstor.getLzeOdemknoutHracem()){
            
            return "Tuto místnost nemůžeš odemknout sám, musíš splnit nějaký úkol.";
        }    
        if(batoh.obsahujeVec(nazevKlice)) {
            
            sousedniProstor.setZamceno(false);
            batoh.vyhodVec(nazevKlice);
            updateHerniPlan();
            return "Odemkl jsi " + odemknout+"\n";
        }
        
        return "K odemčení " + odemknout +" potřebuješ " +nazevKlice+"\n";
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
