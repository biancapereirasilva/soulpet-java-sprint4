package br.com.fiap.resource;

import br.com.fiap.model.bo.MissaoBO;
import br.com.fiap.model.to.MissaoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//resource - equivalente a controller
@RestController
@RequestMapping("/missoes")
public class MissaoResource {
    private MissaoBO missaoBO = new MissaoBO();

    @GetMapping
    public ResponseEntity<List<MissaoTO>> findAll() {
        List<MissaoTO> missoes = missaoBO.findAll();
        if (missoes != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(missoes);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable Long codigo) {
        MissaoTO missao = missaoBO.findByCodigo(codigo);
        if (missao != null) {
            return ResponseEntity.status(HttpStatus.OK).body(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid MissaoTO missao) {
        try {
            MissaoTO resultado = missaoBO.save(missao);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar missão");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (missaoBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> update(@PathVariable Long codigo, @RequestBody @Valid MissaoTO missao) {
        try {
            missao.setCodigo(codigo);
            MissaoTO resultado = missaoBO.update(missao);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não encontrada!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
