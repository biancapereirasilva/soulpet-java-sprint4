package br.com.fiap.resource;

import br.com.fiap.model.bo.UsuarioBO;
import br.com.fiap.model.to.UsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    @GetMapping
    public ResponseEntity<List<UsuarioTO>> findAll() {
        List<UsuarioTO> usuarios = usuarioBO.findAll();
        if (usuarios != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarios);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable Long codigo) {
        UsuarioTO usuario = usuarioBO.findByCodigo(codigo);
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioTO usuario) {
        try {
            UsuarioTO resultado = usuarioBO.save(usuario);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (usuarioBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> update(@PathVariable Long codigo, @RequestBody @Valid UsuarioTO usuario) {
        try {
            usuario.setCodigo(codigo);
            UsuarioTO resultado = usuarioBO.update(usuario);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
