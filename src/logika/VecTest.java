/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy.
 */
package logika;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code VecTest} slouží ke komplexnímu otestování
 * třídy {@link VecTest}.
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class VecTest
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private Vec vec1_sebratelna;
    private Vec vec2;
    Map<String, Vec> veci;
    
    //== PŘÍPRAVA A ÚKLID PŘÍPRAVKU ============================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        vec1_sebratelna = new Vec("klic", true, false);
        vec2            = new Vec("truhla", false, false);
        veci            = new HashMap<>();

    }

    //##########################################################################
    //== VLASTNÍ TESTY =========================================================
   @Test
   public void tesVec()
   {       
       assertEquals(true, vec1_sebratelna.getNazev().equals("klic"));
       assertEquals(false, vec2.getNazev().equals("klic"));
       
       assertFalse(vec2.getLzeProzkoumat());
       vec2.setLzeProzkoumat(true);
       assertTrue(vec2.getLzeProzkoumat());
       
       veci.put(vec1_sebratelna.getNazev(), vec1_sebratelna);
       
       vec2.vlozVec(vec1_sebratelna);
       assertEquals(veci, vec2.vratSeznamVeci());
       
       vec2.setUtok(2);
       assertEquals(2, vec2.getUtok());
       
       assertEquals(true, vec1_sebratelna.isSebratelna());
       assertEquals(false, vec2.isSebratelna());
   }

}
