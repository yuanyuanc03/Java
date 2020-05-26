package com.exemple.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.exemple.beans.Utilisateur;

public class UtilisateurDAO extends DAOContext {
    public static Boolean isValidLogin( String login, String password ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            // String strSql = "SELECT * FROM T_Users WHERE login='" + login +
            // "' AND password='" + password + "'";
            String strSql = "SELECT * FROM Utilisateur WHERE email=? AND mot_de_passe=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                statement.setString( 2, password );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static Boolean isValidEmail( String login ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {

            String strSql = "SELECT * FROM Utilisateur WHERE email=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static void creerUtilisateur( Utilisateur utilisateur ) {
        try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {

            String strSql = "INSERT INTO Utilisateur (email, mot_de_passe, nom, isadmin, date_inscription) VALUES (?, ?, ?, ?, NOW())";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
                statement.setString( 1, utilisateur.getEmail() );
                statement.setString( 2, utilisateur.getMotDePasse() );
                statement.setString( 3, utilisateur.getNom() );
                statement.setBoolean( 4, utilisateur.getAdmin() );
                statement.executeUpdate();
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
    
    public static Utilisateur getUtilisateur(String email) {
    	try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            String strSql = "SELECT * FROM Utilisateur WHERE email=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, email );
                Utilisateur utilisateur = new Utilisateur();
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        utilisateur.setId(resultSet.getLong("id"));
                        utilisateur.setEmail(resultSet.getString("email"));
                        utilisateur.setMotDePasse(resultSet.getString("mot_de_passe"));
                        utilisateur.setNom(resultSet.getString("nom"));
                        utilisateur.setAdmin(resultSet.getBoolean("isadmin"));
                        utilisateur.setDateInscription(resultSet.getTimestamp("date_inscription"));
                    }
                }
                return utilisateur;
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
    
    public static List<Utilisateur> getAllNormalUser(){
    	try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            String strSql = "SELECT * FROM Utilisateur WHERE isadmin=false";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                List<Utilisateur> users = new ArrayList<Utilisateur>();
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    while ( resultSet.next() ) {
                    	Utilisateur utilisateur = new Utilisateur();
                        utilisateur.setId(resultSet.getLong("id"));
                        utilisateur.setEmail(resultSet.getString("email"));
                        utilisateur.setMotDePasse(resultSet.getString("mot_de_passe"));
                        utilisateur.setNom(resultSet.getString("nom"));
                        utilisateur.setAdmin(resultSet.getBoolean("isadmin"));
                        utilisateur.setDateInscription(resultSet.getTimestamp("date_inscription"));
                        users.add(utilisateur);
                    }
                }
                return users;
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
    
    public static void deleteUser(Long id) {
    	try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {
            String strSql = "DELETE FROM Utilisateur WHERE id=?";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
                statement.setLong( 1, id );
                statement.executeUpdate();
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
}
