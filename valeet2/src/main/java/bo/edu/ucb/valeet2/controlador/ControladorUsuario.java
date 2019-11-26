package bo.edu.ucb.valeet2.controlador;

import bo.edu.ucb.valeet2.modelo.Usuario;
import bo.edu.ucb.valeet2.repositorio.RepositorioUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/usuarios"})

public class ControladorUsuario {

    private RepositorioUsuario repositorio;

    ControladorUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorio = repositorioUsuario;
    }

    @GetMapping
    public List findAll(){
        return repositorio.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Usuario> findById(@PathVariable long id){
        return repositorio.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return repositorio.save(usuario);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") long id,
                                          @RequestBody Usuario usuario){
        return repositorio.findById(id)
                .map(record -> {
                    record.setNombre(usuario.getNombre());
                    record.setApellido(usuario.getApellido());
                    record.setCi(usuario.getCi());
                    record.setEmail(usuario.getEmail());
                    record.setPassword(usuario.getPassword());
                    Usuario updated = repositorio.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repositorio.findById(id)
                .map(record -> {
                    repositorio.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
