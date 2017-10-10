package logika;
import javax.swing.*;
import java.net.URL;

/**
 *  Třída PrikazPlanek implementuje pro hru příkaz planek.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazPlanek implements IPrikaz {
    
    private static final String NAZEV = "planek";
    
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan, po kterém se chodí
    */
    public PrikazPlanek(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     *  Provádí příkaz "planek". Zobrazí plánek hry.
     *
     *@param parametry - bez parametrů
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        URL umisteniObrazku = this.getClass().getResource("PlanHry2.jpg");
        URL umisteniObrazku2 = this.getClass().getResource("PlanHry1.jpg");
        
        if(plan.vyberProstor("jeskyně").isViditelna() && umisteniObrazku != null){
            final ImageIcon icon = new ImageIcon(umisteniObrazku);
            JOptionPane.showMessageDialog(null,"","Plánek", JOptionPane.INFORMATION_MESSAGE, icon); 
        }
        else if(plan.vyberProstor("jeskyně").isViditelna() == false && umisteniObrazku2 != null){
            final ImageIcon icon = new ImageIcon(umisteniObrazku2);
            JOptionPane.showMessageDialog(null,"","Plánek", JOptionPane.INFORMATION_MESSAGE, icon); 
        }

        return "Tak snad už víš, kudy se vydat.\n";
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
