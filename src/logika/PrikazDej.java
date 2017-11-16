package logika;

/*******************************************************************************
 *Třída PrikazDej implementuje pro hru příkaz dej.
 *
 * @author    Jakub Jaroš
 * @version   1.0
 */
public class PrikazDej implements IPrikaz
{
    private static final String NAZEV = "dej";
    private HerniPlan plan;
    private Batoh batoh;    

    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se vkládají věci
    */ 
    public PrikazDej(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
        
    }

    /**
     *  Vykonává příkaz "dej". Dá věc postavě, pokud ji má hráč v batohu.
     *  
     *@param parametry  - věc, co se má dát
     *@param parametry  - komu dát věc
     *
     *@return zpráva, která se vypíše hráčovi
     */ 
    public String proved(String... parametry){
        if (parametry.length == 0 || parametry.length == 1) {
            
            return "Nevím komu mám co dát! Tvar příkazu: dej [věc] [postava]\n";  
        }
        String odpoved = "";
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        String vec = parametry[0];
        String jmenoPostavy = parametry[1];
        Postava postava = aktualniProstor.vyberPostavu(jmenoPostavy);

        if(!aktualniProstor.obsahujePostavu(jmenoPostavy)){
            
            return "Taková postava se v prostoru nenachází\n";
        }
        if(!postava.getLzeDat()){
            
            return "Této postavě nemůžeš nic dát\n";
        }
        if(!batoh.obsahujeVec(vec)){
              
            return "Tuto věc nemáš v batohu\n";
        }
        if(jmenoPostavy.equals("trpaslik")){          
            if(!vec.equals("diamant")){

                return "Tuto věc nemůžeš trpaslíkovi dát\n";
            } else{
            
            batoh.vyhodVec(vec);
            batoh.vlozVec(plan.getKlic());
            odpoved = "Získal si klíč od skrýše\n";
            }
        }
        
        return odpoved;
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */

    public String getNazev()
    {
        return NAZEV;
    }
    
    @Override
    public void updateHerniPlan() {
        plan.notifyObservers();
    }
}
