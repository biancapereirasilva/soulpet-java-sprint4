package br.com.fiap.resource;

import br.com.fiap.model.bo.UsuarioMissaoBO;
import br.com.fiap.model.to.UsuarioMissaoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-missoes")
public class UsuarioMissaoResource {
    private UsuarioMissaoBO usuarioMissaoBO = new UsuarioMissaoBO();

    @GetMapping
    public ResponseEntity<List<UsuarioMissaoTO>> findAll() {
        List<UsuarioMissaoTO> usuarioMissoes = usuarioMissaoBO.findAll();
        if (usuarioMissoes != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioMissoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioMissoes);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable Long codigo) {
        UsuarioMissaoTO usuarioMissao = usuarioMissaoBO.findByCodigo(codigo);
        if (usuarioMissao != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioMissao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão do usuário não encontrada!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioMissaoTO usuarioMissao) {
        try {
            UsuarioMissaoTO resultado = usuarioMissaoBO.save(usuarioMissao);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar missão do usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable Long codigo) {
        if (usuarioMissaoBO.delete(codigo)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> update(@PathVariable Long codigo, @RequestBody @Valid UsuarioMissaoTO usuarioMissao) {
        try {
            usuarioMissao.setCodigo(codigo);
            UsuarioMissaoTO resultado = usuarioMissaoBO.update(usuarioMissao);
            if (resultado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(resultado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão do usuário não encontrada!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
