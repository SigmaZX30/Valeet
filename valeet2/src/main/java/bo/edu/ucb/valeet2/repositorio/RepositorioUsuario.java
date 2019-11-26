package bo.edu.ucb.valeet2.repositorio;

import bo.edu.ucb.valeet2.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long>  {
}
