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

@Stateless
public class SportsBean {

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

    public boolean addGame(String body) {
        System.out.println(body);
        JsonReader jsonReader = Json.createReader(new StringReader(body));
        System.out.println(body);
        JsonObject data = jsonReader.readObject();

        System.out.println("fuck");
        jsonReader.close();

        int hl = data.getInt("hemmalag");
        int bl = data.getInt("bortalag");
        int ph = data.getInt("poänghemma");
        int pb = data.getInt("poängborta");
        //regler
        if ((ph + pb == 3) && (hl > 0 && hl <= 10) && (bl > 0 && bl <= 10)) {
            try {
                Connection connection = ConnectionFactory.make("testserver");
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO matcher VALUES(NULL,?,?,?,?");
                stmt.setInt(1, hl);
                stmt.setInt(2, bl);
                stmt.setInt(3, ph);
                stmt.setInt(4, pb);
                stmt.executeUpdate();
                connection.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

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

    public boolean changeGame(String body, int id) {
        JsonReader jsonReader = Json.createReader(new StringReader(body));
        System.out.println(body);
        JsonObject data = jsonReader.readObject();

        System.out.println("fuck");
        jsonReader.close();

        int hl = data.getInt("hemmalag");
        int bl = data.getInt("bortalag");
        int ph = data.getInt("poänghemma");
        int pb = data.getInt("poängborta");
        //regler
        if ((ph + pb == 3) && (hl > 0 && hl <= 10) && (bl > 0 && bl <= 10)) {
            try {
                Connection connection = ConnectionFactory.make("testserver");
                PreparedStatement stmt = connection.prepareStatement("UPDATE matcher\n"
                        + "SET VALUES(NULL,?,?,?,?)\n"
                        + "WHERE id =?");
                stmt.setInt(1, hl);
                stmt.setInt(2, bl);
                stmt.setInt(3, ph);
                stmt.setInt(4, pb);
                stmt.setInt(5, id);
                stmt.execute();
                connection.close();
                return true;
            }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
        }else{
            System.out.println("Fuck");
                    return false;}
    
    }
}