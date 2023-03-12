package test;

import datos.*;
import domain.*;
import java.sql.*;

/**
 *
 * @author Sebastian
 */
public class TestManejoPersonas {

    public static void main(String[] args) {
        Connection conexion = null;

        /* PERSONAS */
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PersonaDAO personaDao = new PersonaDAO(conexion);
            Persona cambioPersona = new Persona();
            cambioPersona.setIdPersona(2);
            cambioPersona.setNombre("Ivone");
            cambioPersona.setApellido("Gomez");
            cambioPersona.setEmail("ivone@gmail.com");
            cambioPersona.setTelefono("316916086");
            personaDao.actualizar(cambioPersona);

            Persona nuevaPersona = new Persona("Pedro", "Pascal", "pedro@gmail.com", "3316916086");
            personaDao.insertar(nuevaPersona);

            conexion.commit(); //En caso de que no haya ninguna excepcion, se hace ejecutan los cambios
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback(); //En caso de que haya excepcion se hace rollback, no se ejecuta ningun cambio
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }

        /* USUARIOS */
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            UsuarioDAO usuarioDao = new UsuarioDAO(conexion);
            Usuario nuevoUsuario = new Usuario("Juan","juan1234");
            usuarioDao.insertar(nuevoUsuario);
            conexion.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }

    }
}
