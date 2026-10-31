package br.com.fiap.resource;

import br.com.fiap.model.bo.RecompensaBO;
import br.com.fiap.model.to.RecompensaTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recompensas")
public class RecompensaResource {
    private RecompensaBO recompensaBO = new RecompensaBO();

    @GetMapping
    public ResponseEntity<List<RecompensaTO>> findAll() {
        List<RecompensaTO> recompensas = recompensaBO.findAll();
        if (recompensas != null) {
            return ResponseEntity.status(HttpStatus.OK).body(recompensas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(recompensas);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable Long codigo) {
        RecompensaTO recompensa = recompensaBO.findByCodigo(codigo);
        if (recompensa != null) {
            return ResponseEntity.status(HttpStatus.OK).body(recompensa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recompensa não encontrada!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid RecompensaTO recompensa) {
        try {
            RecompensaTO resultado = recompensaBO.save(recompensa);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar recompensa");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (recompensaBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> update(@PathVariable Long codigo, @RequestBody @Valid RecompensaTO recompensa) {
        try {
            recompensa.setCodigo(codigo);
            RecompensaTO resultado = recompensaBO.update(recompensa);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recompensa não encontrada!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
