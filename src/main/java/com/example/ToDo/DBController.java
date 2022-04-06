package com.example.ToDo;
import com.example.ToDo.models.Todo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class DBController {

    String connectionUrl;
    String username;
    String passwort;
   
    public DBController(){
        setConnectionUrl("jdbc:mysql://localhost:8889/javadb");
        setPasswort("root");
        setUsername("root");
    }

    // Holt alle ToDos aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Todo> getAllToDos(){

        // Lokale Todos-Arraylist erstellen
        ArrayList<Todo> todos = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllPersons = "SELECT * FROM todos";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String person = rs.getString("person");
                String description = rs.getString("description");
                todos.add(new Todo(id, description, person));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return todos;
    }

    // Füge neue Todo hinzu
    public void addNewToDo(String person, String description) {
        try{
            String sqlSelectAllPersons = "INSERT INTO todos(person,description) VALUES('"+person+"','"+description+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche eine Todo
    public void delToDo(int id){
        try{

            String sqlSelectAllPersons = "DELETE FROM todos WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifische Todo
    public Todo getToDo(int id){
        Todo todo = null;
        try{
            String sqlSelectAllPersons = "SELECT * FROM todos WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int todoId = (int) rs.getLong("id");
                String person = rs.getString("person");
                String description = rs.getString("description");
                todo = new Todo(todoId, description, person);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return todo;
    }

    // Hole spezifische Todo und aktualisiere diese ab
    public Todo updateToDo(int id, String person, String description){
        Todo todo = null;
        try{
            String sqlSelectAllPersons = "UPDATE todos SET person='"+person+"', description='"+description+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return todo;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getUsername() {
        return username;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    
}
