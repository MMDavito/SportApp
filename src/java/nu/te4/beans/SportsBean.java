/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import com.mysql.jdbc.Connection;
import java.sql.Statement;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.json.JsonArray;
import nu.te4.support.ConnectionFactory;

@Named
@RequestScoped
public class SportsBean {
    
    public JsonArray getGames(){
        try {
                    Connection connection = ConnectionFactory.make("testserver");
                    Statement stmt = connection.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
