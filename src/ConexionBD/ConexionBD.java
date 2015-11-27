/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author usuario
 */
public class ConexionBD {
    
    private Connection conexion;
            
    
    public ConexionBD(){
        try{
            Class.forName( "com.mysql.jdbc.Driver" );
            String host = "jdbc:mysql://localhost/snp_bd";
            String user = "root";
            String pass = "";
            this.conexion = DriverManager.getConnection( host, user, pass );
        }
        catch ( ClassNotFoundException error ){
            error.printStackTrace();
        }
        catch ( SQLException error ){
            error.printStackTrace();
        }        
    }
    
    
    public void insertarSNP( int idSnp, String nombre ){
        try{
            String insertSnpSQL = "INSERT INTO snp( idSnp, nombre ) VALUES (?, ?)";
            PreparedStatement stat = this.conexion.prepareStatement( insertSnpSQL );
            stat.setInt( 1, idSnp );
            stat.setString( 2 , nombre );
            stat.executeUpdate();            
        }
        catch ( SQLException error ){
            error.printStackTrace();
        }
    }
    
    
    public boolean consultarSNP( int idSnp ){
        try{
            String selectSnpSQL = "SELECT idSnp FROM snp WHERE idSnp = ?";
            PreparedStatement stat = this.conexion.prepareStatement( selectSnpSQL );
            stat.setInt( 1, idSnp );
            ResultSet result = stat.executeQuery();
            if ( result.getInt( 1 ) == idSnp ){
                return true;
            }
            else{
                return false;
            }            
        }
        catch ( SQLException error ){
            error.printStackTrace();
        }
        return false;
    }
    
    
    public boolean consultarCalculo( int idCalculo ){
        try{
            String selectCalculoSQL = "SELECT idCalculo FROM calculo WHERE idCalculo = ?";
            PreparedStatement stat = this.conexion.prepareStatement( selectCalculoSQL );
            stat.setInt( 1, idCalculo );
            ResultSet result = stat.executeQuery();
            if ( result.getInt( 1 ) == idCalculo ){
                return true;
            }
            else{
                return false;
            }            
        }
        catch ( SQLException error ){
            error.printStackTrace();
        }
        return false;
    }
    
    public void insertarCalculo( int idCalculo, String tipo, int idSnp ) throws Exception{
        try{            
            String insertCalculoSQL = "INSERT INTO calculo( idCalculo, tipo, snp_idSnp ) VALUES ( ?, ?, ? )";
            PreparedStatement stat = this.conexion.prepareStatement( insertCalculoSQL );
            stat.setInt( 1, idCalculo );
            stat.setString( 2, tipo  );
            stat.setInt( 3, idSnp );
            stat.executeUpdate();                                  
        }
        catch ( SQLException error ){
            error.printStackTrace();
            throw new Exception( error.getMessage() );
            
        }
    }
    
    public void insertarFrecuenciasAlelicas( int idFrecuenciasAlelicas, String total, String alelo, String frec, int idSnp, String tipo ) throws Exception{
        try{            
            String insertFrecAlelicasSQL = "INSERT INTO FrecuenciasAlelicas( idFrecuenciasAlelicas, total, alelo, frec, snp_idSnp, tipo ) VALUES ( ?, ?, ?, ?, ?, ? )";
            PreparedStatement stat = this.conexion.prepareStatement( insertFrecAlelicasSQL );
            stat.setInt( 1, idFrecuenciasAlelicas );
            stat.setString( 2, total  );
            stat.setString( 3, alelo  );
            stat.setString( 4, frec  );
            stat.setInt( 5, idSnp );
            stat.setString( 6, tipo  );
            stat.executeUpdate();                      
        }
        catch ( SQLException error ){
            error.printStackTrace();
            throw new Exception( error.getMessage() );
        }
    }
    
    public void insertarFrecuenciasGenotipicas( int idFrecuenciasGenotipicas, String total, String genotipo, String frec, int idSnp, String tipo ) throws Exception{
        try{            
            String insertFrecAlelicasSQL = "INSERT INTO FrecuenciasGenotipicas( idFrecuenciasGenotipicas, total, genotipo, frec, snp_idSnp, tipo ) VALUES ( ?, ?, ?, ?, ?, ? )";
            PreparedStatement stat = this.conexion.prepareStatement( insertFrecAlelicasSQL );
            stat.setInt( 1, idFrecuenciasGenotipicas );
            stat.setString( 2, total  );
            stat.setString( 3, genotipo  );
            stat.setString( 4, frec  );
            stat.setInt( 5, idSnp );
            stat.setString( 6, tipo );
            stat.executeUpdate();                      
        }
        catch ( SQLException error ){
            error.printStackTrace();
            throw new Exception( error.getMessage() );
        }
    }
    
    public void insertarEquilibrioHW( int idEquilibrioHW, String genotipos, String observados, String esperados, String chi2, int idSnp, String tipo ) throws Exception{
        try{            
            String insertFrecAlelicasSQL = "INSERT INTO EquilibrioHW( idEquilibrioHW, genotipos, observados, esperados, chi2, snp_idSnp, tipo ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
            PreparedStatement stat = this.conexion.prepareStatement( insertFrecAlelicasSQL );
            stat.setInt( 1, idEquilibrioHW );
            stat.setString( 2, genotipos  );
            stat.setString( 3, observados  );
            stat.setString( 4, esperados  );
            stat.setString( 5, chi2  );
            stat.setInt( 6, idSnp );
            stat.setString( 7, tipo );
            stat.executeUpdate();                      
        }
        catch ( SQLException error ){
            error.printStackTrace();
            throw new Exception( error.getMessage() );
        }
    }
    
    
 
    
    
    public void cerrarConexion(){
        try{
            this.conexion.close();
        }
        catch ( SQLException error ){
            error.printStackTrace();
        }
    }
    
}
