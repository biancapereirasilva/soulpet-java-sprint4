package br.com.fiap.resource;

import br.com.fiap.model.bo.UsuarioRecompensaBO;
import br.com.fiap.model.to.UsuarioRecompensaTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-recompensas")
public class UsuarioRecompensaResource {
    private UsuarioRecompensaBO usuarioRecompensaBO = new UsuarioRecompensaBO();

    @GetMapping
    public ResponseEntity<List<UsuarioRecompensaTO>> findAll() {
        List<UsuarioRecompensaTO> resgates = usuarioRecompensaBO.findAll();
        if (resgates != null) {
            return ResponseEntity.status(HttpStatus.OK).body(resgates);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resgates);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable Long codigo) {
        UsuarioRecompensaTO usuarioRecompensa = usuarioRecompensaBO.findByCodigo(codigo);
        if (usuarioRecompensa != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRecompensa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resgate não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioRecompensaTO usuarioRecompensa) {
        try {
            UsuarioRecompensaTO resultado = usuarioRecompensaBO.save(usuarioRecompensa);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar resgate");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (usuarioRecompensaBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> update(@PathVariable Long codigo, @RequestBody @Valid UsuarioRecompensaTO usuarioRecompensa) {
        try {
            usuarioRecompensa.setCodigo(codigo);
            UsuarioRecompensaTO resultado = usuarioRecompensaBO.update(usuarioRecompensa);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resgate não encontrado!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
