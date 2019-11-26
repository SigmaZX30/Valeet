package bo.edu.ucb.valeet.controlador;

import java.util.List;

import antlr.collections.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bo.edu.ucb.valeet.entidad.Usuario;
import bo.edu.ucb.valeet.repositorio.RepositorioUsuario;

@Controller
@RequestMapping(path = "/usuario")

public class ControladorUsuario {

    @Autowired
    RepositorioUsuario repositorioUsuario;

    @GetMapping(path = "/agregar")
    @ResponseBody
    public String agregarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam int ci, @RequestParam String email, @RequestParam String password) {

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCi(ci);
        usuario.setEmail(email);
        usuario.setPassword(password);

        repositorioUsuario.save(usuario);

        String ret = "Se creo la cuenta, nombre = " + nombre + ", apellido = " + apellido + ", ci = " + ci + ", email = " + email +", password = "
                + password;

        return ret;

    }

    @GetMapping(path = "/listar")
    @ResponseBody
    public String listarUsuario() {

        StringBuffer retBuf = new StringBuffer();

        List<Usuario> userAccountList = (List<Usuario>) repositorioUsuario.findAll();

        if (userAccountList != null) {
            for (Usuario usuario : userAccountList) {
                retBuf.append("nombre = ");
                retBuf.append(usuario.getNombre());
                retBuf.append("apellido = ");
                retBuf.append(usuario.getApellido());
                retBuf.append("ci = ");
                retBuf.append(usuario.getCi());
                retBuf.append(", email = ");
                retBuf.append(usuario.getEmail());
                retBuf.append("password = ");
                retBuf.append(usuario.getPassword());
                retBuf.append("\r\n");
            }
        }

        if (retBuf.length() == 0) {
            retBuf.append("No hay registros.");
        } else {
            retBuf.insert(0, "<pre>");
            retBuf.append("</pre>");
        }

        return retBuf.toString();
    }

    @GetMapping(path = "/buscar")
    @ResponseBody
    public String buscarUsuario(@RequestParam String nombre) {

        StringBuffer retBuf = new StringBuffer();

        List<Usuario> userAccountList = (List<Usuario>) repositorioUsuario.findByUsername(nombre);

        if (userAccountList != null) {
            for (Usuario usuario : userAccountList) {
                retBuf.append("nombre = ");
                retBuf.append(usuario.getNombre());
                retBuf.append("apellido = ");
                retBuf.append(usuario.getApellido());
                retBuf.append("ci = ");
                retBuf.append(usuario.getCi());
                retBuf.append(", email = ");
                retBuf.append(usuario.getEmail());
                retBuf.append("password = ");
                retBuf.append(usuario.getPassword());
                retBuf.append("\r\n");
            }
        }

        if (retBuf.length() == 0) {
            retBuf.append("No hay registros.");
        }

        return retBuf.toString();
    }

    @GetMapping(path = "/actualizar")
    @ResponseBody
    public String actualizarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam int ci, @RequestParam String email, @RequestParam String password) {

        StringBuffer retBuf = new StringBuffer();

        List<Usuario> userAccountList = repositorioUsuario.findByUsername(nombre);

        if (userAccountList != null) {
            for (Usuario usuario : userAccountList) {
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setCi(ci);
                usuario.setEmail(email);
                usuario.setPassword(password);
                repositorioUsuario.save(usuario);
            }
        }

        retBuf.append("Informacion actualizada exitosamente.");

        return retBuf.toString();
    }
    @GetMapping(path = "/eliminar")
    @ResponseBody
    public String eliminar(@RequestParam String nombre) {

        StringBuffer retBuf = new StringBuffer();

        repositorioUsuario.deleteByUsername(nombre);

        retBuf.append("Usuario eliminado exitosamente.");

        return retBuf.toString();
    }
}
