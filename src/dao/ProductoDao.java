/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Conexion.Conexion;
import Interfaces.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import modelo.producto;

/**
 *
 * @author LN710Q
 */
public class ProductoDao implements metodos<producto>{
    
    private static final String SQL_INSERT = "INSERT INTO productos(nombre, codigo, tipo, cantidad, precio, disponibilidad) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE `productos` SET `nombre`=?,`tipo`=?,`cantidad`=?,`precio`=?,`disponibilidad`=? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM `productos` WHERE codigo = ?";
    private static final String SQL_READ = "SELECT * FROM `productos` WHERE codigo =?";
    private static final String SQL_READALL = "SELECT * FROM `productos`";
    
           Conexion con = Conexion.conectar();

    @Override
    public boolean create(producto g) {
     PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getCodigo());
            ps.setString(3, g.getTipo());
            ps.setInt(4, g.getCantidad());
            ps.setDouble(5, g.getPrecio());
            ps.setInt(6, g.getDisponibilidad());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        } finally {
            con.cerrarConexion();
        }
        return false;

    }

    @Override
    public boolean delete(Object key) {
         PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());

            if(ps.executeUpdate() >0){
                return true;
            
            }
            
        }catch(SQLException ex){
     
        
        }finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(producto c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getId());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            
            ps.setString(2, c.getTipo());
            ps.setInt(3, c.getCantidad());
            ps.setDouble(4, c.getPrecio());
            ps.setInt(5, c.getDisponibilidad());
            ps.setString(6, c.getCodigo());

            if(ps.executeUpdate() >0){
                return true;
            
            }
            
        }catch(SQLException ex){
           System.out.println(ex.getMessage());
            
        
        }finally {
            con.cerrarConexion();
        }
        return false;
        
    }

    @Override
    public producto read(Object key) {
        producto f = null;
           PreparedStatement ps;
           ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            
            while(rs.next()){
                f =new producto(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7));
            
            }
            rs.close();
        }catch(SQLException ex){
           System.out.println(ex.getMessage());
            
        
        }finally {
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<producto> readAll() {
        ArrayList<producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
        s = con.getCnx().prepareStatement(SQL_READALL);
        rs = s.executeQuery(SQL_READALL);
        while(rs.next()){
            all.add(new producto(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getInt(7)));
        }
        rs.close();
        }catch(SQLException ex){
        
        
        }
        return all;
        
    }

  
    
}
