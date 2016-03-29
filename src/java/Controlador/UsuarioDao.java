/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author stein
 */
public class UsuarioDao {
    private Session sesion;
    private Transaction tx;
    private void iniciaOperacion() throws HibernateException
{
    sesion = HibernateUtil.getSessionFactory().openSession();
    tx = sesion.beginTransaction();
}
    private void manejaExcepcion(HibernateException he) throws HibernateException
{
    tx.rollback();
    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
}
    public long guardaUsuario(Usuario contacto)
{ 
    long id = 0;  

    try 
    { 
        iniciaOperacion(); 
        id = (Long)sesion.save(contacto); 
        tx.commit(); 
    }catch(HibernateException he) 
    { 
        manejaExcepcion(he);
        throw he; 
    }finally 
    { 
        sesion.close(); 
    }  
    return id; 
}
    
public void actualizaUsuario(Usuario contacto) throws HibernateException 
{ 
    try 
    { 
        iniciaOperacion(); 
        sesion.update(contacto); 
        tx.commit(); 
    }catch (HibernateException he) 
    { 
        manejaExcepcion(he); 
        throw he; 
    }finally 
    { 
        sesion.close(); 
    } 
}
    public void eliminaUsuario(Usuario contacto) throws HibernateException 
{ 
    try 
    { 
        iniciaOperacion(); 
        sesion.delete(contacto); 
        tx.commit(); 
    } catch (HibernateException he) 
    { 
        manejaExcepcion(he); 
        throw he; 
    }finally 
    { 
        sesion.close(); 
    } 
}
 public Usuario obtenUsuario(long idUsuario) throws HibernateException
{ 
    Usuario contacto = null;  

    try 
    { 
        iniciaOperacion(); 
        contacto = (Usuario) sesion.get(Usuario.class, idUsuario); 
    } finally 
    { 
        sesion.close(); 
    }  
    return contacto; 
}   
 public List<Usuario> obtenListaUsuarios() throws HibernateException 
{ 
    List<Usuario> listaUsuarios = null;  
    
    try 
    { 
        iniciaOperacion(); 
        listaUsuarios = sesion.createQuery("from Usuario").list(); 
    }finally 
    { 
        sesion.close(); 
    }  

    return listaUsuarios; 
}
 public Usuario getUsuario(String nombre, String contrasenia){
     List<Usuario> listaUsuarios = obtenListaUsuarios();
     for (Usuario user : listaUsuarios){
         if(user.getUsuario() == nombre && user.getContrasenia() == contrasenia)
             return user;
     }
     return null;
 
 }
}
