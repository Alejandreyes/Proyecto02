package DAO;
// Generated 29/03/2016 03:33:27 PM by Hibernate Tools 4.3.1



/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int id;
     private String usuario;
     private String contrasenia;

    public Usuario() {
    }

	
    public Usuario(int id) {
        this.id = id;
    }
    public Usuario(int id, String usuario, String contrasenia) {
       this.id = id;
       this.usuario = usuario;
       this.contrasenia = contrasenia;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }




}


