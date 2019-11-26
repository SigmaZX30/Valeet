package bo.edu.ucb.valeet.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import bo.edu.ucb.valeet.entidad.Usuario;


public interface RepositorioUsuario extends CrudRepository<Usuario, Long>{

    List<Usuario> findByUsername(String nombre);

    @Transactional
    void deleteByUsername(String nombre);
}
