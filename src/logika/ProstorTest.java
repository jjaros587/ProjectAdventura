/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy.
 */
package logika;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code ProstorTest} slouží ke komplexnímu otestování
 * třídy {@link ProstorTest}.
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class ProstorTest
{
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private Prostor prostor1;
    private Prostor prostor2;
    private Vec vec;
    private Vec vec_klic;
    private Postava postava;
    

    //== PŘÍPRAVA A ÚKLID PŘÍPRAVKU ============================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
//        prostor1 = new Prostor("hospoda", "V hospodě si můžeš doplnit životy");
//        prostor2 = new Prostor("rozcestí", "Rozcestí vede na mnoho míst");
        vec = new Vec("meč", true, true);
        vec_klic = new Vec("klic", true, false);
        postava = new Postava("drak", "Drak hlídá pokladnici", true);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */

    //##########################################################################
    //== VLASTNÍ TESTY =========================================================
     @Test
    public void testProstor()
    {
        assertTrue(prostor1.getNazev().equals("hospoda"));
        assertFalse(prostor1.getNazev().equals("prostor"));
        
        prostor1.setVychod(prostor2);
        
        assertEquals(prostor2, prostor1.vratSousedniProstor("rozcestí"));
        
        
        
        assertNotNull(prostor1.getVychody());
       
        Collection<Prostor> seznam = prostor1.getVychody();
       
        assertEquals(1, seznam.size());
        
        
            
        prostor2.setViditelna(false);
        
        prostor2.setZamceno(true);
        assertTrue(prostor2.jeZamceno());
        assertFalse(prostor1.jeZamceno());
        
        assertEquals("vychody: rozcestí(zamknuto)", prostor1.popisVychodu());
        
        prostor2.setKlic(vec_klic);
        assertEquals(vec_klic, prostor2.getKlic());
        
        prostor1.vlozVec(vec);
        prostor1.vlozPostavu(postava);
      
        
        assertTrue(prostor1.obsahujeVec("meč"));
        assertFalse(prostor1.obsahujeVec("sgsdg"));
        assertEquals(vec, prostor1.vyberVec("meč"));
        assertEquals("veci: meč", prostor1.popisVeci());
        assertEquals(vec, prostor1.odeberVec("meč"));
        assertFalse(prostor1.obsahujeVec("meč"));

        assertTrue(prostor1.obsahujePostavu("drak"));
        assertFalse(prostor1.obsahujePostavu("sgsdg"));
        assertEquals(postava, prostor1.vyberPostavu("drak"));
        assertEquals("postavy: drak", prostor1.popisPostav());
        assertEquals(postava, prostor1.odeberPostavu("drak"));
        assertFalse(prostor1.obsahujePostavu("drak"));
        
        
        
    }
}
