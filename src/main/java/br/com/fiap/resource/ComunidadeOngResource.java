package br.com.fiap.resource;

import br.com.fiap.model.bo.ComunidadeOngBO;
import br.com.fiap.model.to.ComunidadeOngTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunidades")
public class ComunidadeOngResource {
    private ComunidadeOngBO comunidadeOngBO = new ComunidadeOngBO();

    @GetMapping
    public ResponseEntity<List<ComunidadeOngTO>> findAll() {
        List<ComunidadeOngTO> comunidades = comunidadeOngBO.findAll();
        if (comunidades != null) {
            return ResponseEntity.status(HttpStatus.OK).body(comunidades);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(comunidades);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable Long codigo) {
        ComunidadeOngTO comunidadeOng = comunidadeOngBO.findByCodigo(codigo);
        if (comunidadeOng != null) {
            return ResponseEntity.status(HttpStatus.OK).body(comunidadeOng);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunidade não encontrada!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ComunidadeOngTO comunidadeOng) {
        try {
            ComunidadeOngTO resultado = comunidadeOngBO.save(comunidadeOng);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar comunidade");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (comunidadeOngBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> update(@PathVariable Long codigo, @RequestBody @Valid ComunidadeOngTO comunidadeOng) {
        try {
            comunidadeOng.setCodigo(codigo);
            ComunidadeOngTO resultado = comunidadeOngBO.update(comunidadeOng);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunidade não encontrada!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
