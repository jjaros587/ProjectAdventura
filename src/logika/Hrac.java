package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/*******************************************************************************
 * Instance třídy {@code Hrac} představuje hráče
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class Hrac implements Subject
{
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private int zivoty = 4;
    private int penize = 0;
    private int utok   = 1;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     *Konstruktor pro vytvoření instance třídy hráč,
     *která uchovává počet životů a zlaťáků hráče
     */
    public Hrac()
    {
        this.zivoty = zivoty;
        this.penize = penize;
        this.utok   = utok;
    }
    
    //== NESOUKROMÉ METODY INSTANCÍ ====================================
    /**
     *  Metoda nastaví životy hráče
     *
     *@param  počet životů
     */ 
    public void setZivoty(int zivoty){
        
        this.zivoty = zivoty;
        notifyObservers();
    }
    /**
     *  Metoda nastaví počet zlaťáků, která hráč získal
     *
     *@param  počet zlaťáků
     */ 
    public void setPenize(int penize){
        
        this.penize = penize;
        notifyObservers();
    }
    /**
     *  Metoda nastaví hodnotu útoku hráče
     *
     *@param  útok hráče
     */ 
    public void setUtok(int utok){
        
        this.utok = utok;
        notifyObservers();
    }
    /**
     *  Metoda pro získání počtu životů hráče
     *
     *@return  počet životů hráče
     */ 
    public int getZivoty(){
        
        return zivoty;
    }
    /**
     *  Metoda pro získání počtu zlaťáků
     *
     *@return  počet zlaťáků, které hráč má
     */ 
     public int getPenize(){
        
        return penize;
    }
    /**
     *  Metoda pro hodnoty útoku hráče
     *
     *@return  útok hráče
     */ 
     public int getUtok(){
        
        return utok;
    }
    /**
     *  Metoda pro výpis informací o hráči
     *
     *@return  info o hráči
     */
    public String getInfoOHraci(){
        return "Životy: "  + this.getZivoty() +"\n"
             + "Útok: "    + this.getUtok()   +"\n"
             + "Zlaťáky: " + this.getPenize();
    }
    /**
     * Registrace observeru
     * @param observer Observer
     */
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }
    /**
     * Zrušení observeru
     * @param observer Observer
     */
    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }
    /**
     * Oznámení observeru
     */   
    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
