/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import javax.ejb.embeddable.EJBContainer;
import javax.json.JsonArray;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daca97002
 */
public class SportsBeanTest {
    
    public SportsBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getGames method, of class SportsBean.
     */
   /* @Test
    public void testGetGames() throws Exception {
        System.out.println("getGames");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SportsBean instance = (SportsBean)container.getContext().lookup("java:global/classes/SportsBean");
        JsonArray expResult = null;
        JsonArray result = instance.getGames();
        //assertThat(expResult, is(not(result)));
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGame method, of class SportsBean.
     */
    @Test
    public void testAddGame() throws Exception {
        System.out.println("addGame");
        for (int hl =0;hl<=10; hl++){
        String body = "{\n" +
"	\"hemmalag\": 3,\n" +
"	\"bortalag\": 2,\n" +
"	\"poanghemma\": 0,\n" +
"	\"poangborta\": 3\n" +
"}";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SportsBean instance = (SportsBean)container.getContext().lookup("java:global/classes/SportsBean");
        boolean expResult = true;
        boolean result = instance.addGame(body);
        assertEquals(expResult, result);
        container.close();
        }
    }

    /**
     * Test of deleteGame method, of class SportsBean.
     */
    
    /*@Test
    public void testDeleteGame() throws Exception {
        System.out.println("deleteGame");
        int id = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SportsBean instance = (SportsBean)container.getContext().lookup("java:global/classes/SportsBean");
        boolean expResult = false;
        boolean result = instance.deleteGame(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeGame method, of class SportsBean.
     */
   /* @Test
    public void testChangeGame() throws Exception {
        System.out.println("changeGame");
        String body = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SportsBean instance = (SportsBean)container.getContext().lookup("java:global/classes/SportsBean");
        boolean expResult = false;
        boolean result = instance.changeGame(body);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTable method, of class SportsBean.
     */
   /* @Test
    public void testGetTable() throws Exception {
        System.out.println("getTable");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SportsBean instance = (SportsBean)container.getContext().lookup("java:global/classes/SportsBean");
        JsonArray expResult = null;
        JsonArray result = instance.getTable();
        //assertThat(expResult, is(not(result)));
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeams method, of class SportsBean.
     */
    /*@Test
    public void testGetTeams() throws Exception {
        System.out.println("getTeams");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SportsBean instance = (SportsBean)container.getContext().lookup("java:global/classes/SportsBean");
        JsonArray expResult = null;
        JsonArray result = instance.getTeams();
       // assertThat(expResult, is(not(result)));
        assertEquals(expResult, result);
        /*String poang;
        poang = result.getString(0, poang);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of getTeam method, of class SportsBean.
     */
    /*@Test
    public void testGetTeam() throws Exception {
        System.out.println("getTeam");
        int id = 20;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SportsBean instance = (SportsBean)container.getContext().lookup("java:global/classes/SportsBean");
        JsonArray expResult = null;
        JsonArray result = instance.getTeam(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
