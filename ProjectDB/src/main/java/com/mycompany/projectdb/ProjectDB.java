/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectdb;

/**
 *
 * @author IMANE
 */
import java.sql.*;
import java.util.Scanner;
public class ProjectDB {

    
        static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
        static final String USER = "dam2";
        static final String PASS = "1234";
        static final String QUERY = "SELECT * FROM videojuegos";
        
        public static void main(String[] args){
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(QUERY);
                    Scanner scanner = new Scanner(System.in)) {
                
                while (rs.next()){
                    System.out.println("ID: "+ rs.getInt("id"));
                    System.out.println("Nombre: "+rs.getString("Nombre"));
                    System.out.println("Genero"+rs.getString("Genero"));
                    System.out.println("FechaLanzamiento"+rs.getDate("FechaLanzamiento"));
                    System.out.println("Compañia"+rs.getString("Compañia"));
                    System.out.println("Precio"+rs.getFloat("Precio"));
                    System.out.println("---------------------");
                      
                }
            
                
                // Insertar un nuevo juego
            System.out.println("Ingrese los detalles del nuevo juego:");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Genero: ");
            String genero = scanner.nextLine();

            System.out.print("Fecha de Lanzamiento (YYYY-MM-DD): ");
            String fechaLanzamiento = scanner.nextLine();

            System.out.print("Compañia: ");
            String compania = scanner.nextLine();

            System.out.print("Precio: ");
            float precio = scanner.nextFloat();

            // Crear la consulta preparada
            String insertQuery = "INSERT INTO videojuegos (Nombre, Genero, FechaLanzamiento, Compañia, Precio) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, nombre);
                insertStmt.setString(2, genero);
                insertStmt.setDate(3, Date.valueOf(fechaLanzamiento));
                insertStmt.setString(4, compania);
                insertStmt.setFloat(5, precio);

                // Ejecutar la consulta
                int rowsAffected = insertStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Nuevo juego insertado con éxito.");
              
                }
            }
            
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
           
    }
}
