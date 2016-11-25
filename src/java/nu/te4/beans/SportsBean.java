/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import com.mysql.jdbc.Connection;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import nu.te4.support.ConnectionFactory;

/**
 *
 * @author daca97002
 */
@Stateless
public class SportsBean {

    /**
     *
     * @return returns JsonArray containing all games in database, "id": id (of game),
     * "hemmalag": home team, "bortalag": other team, "poanghemma": points home, "poangborta": points away
     */
    public JsonArray getGames() {
        try {
            Connection connection = ConnectionFactory.make("testserver");
            Statement stmt = connection.createStatement();
            String sql = "SELECT*FROM matcher";
            ResultSet data = stmt.executeQuery(sql);
            //arraybuilder
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            while (data.next()) {
                int id = data.getInt("id");
                int hl = data.getInt("hemmalag");
                int bl = data.getInt("bortalag");
                int hp = data.getInt("poanghemma");
                int bp = data.getInt("poangbort");

                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("id", id)
                        .add("hemmalag", hl)
                        .add("bortalag", bl)
                        .add("poanghemma", hp)
                        .add("poangborta", bp).build());

            }
            connection.close();

            return jsonArrayBuilder.build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param body JsonObject containing "hemmalag": hometeam,
     * "bortalag": other team, "poanghemma": points home, "poangborta": points away
     * @return boolean, True if success, else fail
     */
    public boolean addGame(String body) {
        JsonReader jsonReader = Json.createReader(new StringReader(body));
        System.out.println(body);
        JsonObject data = jsonReader.readObject();

        System.out.println(data);
        jsonReader.close();
        
        int hl = data.getInt("hemmalag");
        int bl = data.getInt("bortalag");
        int ph = data.getInt("poanghemma");
        int pb = data.getInt("poangborta");

        //regler
        if ((ph + pb == 3) && (hl > 0 && hl <= 10) && (bl > 0 && bl <= 10)) {
            try {
                Connection connection = ConnectionFactory.make("testserver");
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO matcher VALUES(NULL,?,?,?,?);");
                stmt.setInt(1, hl);
                stmt.setInt(2, bl);
                stmt.setInt(3, ph);
                stmt.setInt(4, pb);
                stmt.executeUpdate();
                System.out.println(stmt);
                connection.close();                
                return true;
            } catch (Exception e) {
                
                System.err.println("fuck off");
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @param id id of game wished to delete
     * @return boolean true if success else fail
     */
    public boolean deleteGame(int id) {
        try {
            Connection connection = ConnectionFactory.make("testserver");
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM matcher WHERE id =?");
            stmt.setInt(1, id);
            stmt.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     *
     * @param body JsonObject containing "id": id (of game wished to change), "hemmalag": hometeam,
     * "bortalag": other team, "poanghemma": points home, "poangborta": points away
     * @return boolean, True if success, else fail
     */
    public boolean changeGame(String body) {
        JsonReader jsonReader = Json.createReader(new StringReader(body));
        System.out.println(body);
        JsonObject data = jsonReader.readObject();

        jsonReader.close();
        int id = data.getInt("id");
        int hl = data.getInt("hemmalag");
        int bl = data.getInt("bortalag");
        int ph = data.getInt("poanghemma");
        int pb = data.getInt("poangborta");
        //regler
        if ((ph + pb == 3) && (hl > 0 && hl <= 10) && (bl > 0 && bl <= 10)) {
            try {
                Connection connection = ConnectionFactory.make("testserver");
                PreparedStatement stmt = connection.prepareStatement("UPDATE matcher\n"
                        + "SET hemmalag = ?, bortalag = ?, poanghemma = ?, poangbort = ?\n"
                        + "WHERE id =?;");
                stmt.setInt(1, hl);
                stmt.setInt(2, bl);
                stmt.setInt(3, ph);
                stmt.setInt(4, pb);
                stmt.setInt(5, id);
                stmt.execute();
                connection.close();
                return true;
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Fuck, tomt?");
            return false;
        }
    }

    /**
     *
     * @return JsonArray containging all teams names and points 
     * "lagnamn": teamname, "p": points
     * returns null if fail
     */
    public JsonArray getTable() {
        try {
            Connection connection = ConnectionFactory.make("testserver");
            Statement stmt = connection.createStatement();
            String sql = "SELECT*FROM tabellen;";
            ResultSet data = stmt.executeQuery(sql);
            //arraybuilder
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            while (data.next()) {
                String lagnamn = data.getString("lagnamn");
                int p = data.getInt("p");

                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("lagnamn", lagnamn)
                        .add("p", p));

            }
            connection.close();

            return jsonArrayBuilder.build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @return JsonArray containing "id": id, "lag": lagnamn
     * returns null if fail
     */
    public JsonArray getTeams() {
        try {
            Connection connection = ConnectionFactory.make("testserver");
            Statement stmt = connection.createStatement();
            String sql = "SELECT*FROM lag;";
            ResultSet data = stmt.executeQuery(sql);
            //arraybuilder
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            while (data.next()) {
                int id = data.getInt("id");
                String lag = data.getString("namn");

                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("id", id)
                        .add("lag", lag));

            }
            connection.close();

            return jsonArrayBuilder.build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param id Id of team (look database for all teams or call method *getTeams()*
     * @return Returns JsonArray containing "lagnamn":teamname, "p":points ((maybe also id *TBD*))
     * returns null if fail
     */
    public JsonArray getTeam(int id) {
        try {
            Connection con = ConnectionFactory.make("testserver");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM tabellen "
                    + "WHERE lagnamn = (SELECT namn FROM lag WHERE id = ?)");
            stmt.setInt(1, id);
            ResultSet data = stmt.executeQuery();
            //arraybuilder
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            while (data.next()) {
                String lag = data.getString("lagnamn");
                int p = data.getInt("p");

                jsonArrayBuilder.add(Json.createObjectBuilder()
                        .add("lag", lag)
                        .add("p", p));
            }
            con.close();

            return jsonArrayBuilder.build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
