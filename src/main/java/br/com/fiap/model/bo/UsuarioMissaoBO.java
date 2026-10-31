package br.com.fiap.model.bo;

import br.com.fiap.model.dao.UsuarioMissaoDAO;
import br.com.fiap.model.to.UsuarioMissaoTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioMissaoBO {
    private UsuarioMissaoDAO usuarioMissaoDAO = new UsuarioMissaoDAO();

    public ArrayList<UsuarioMissaoTO> findAll() {
        return usuarioMissaoDAO.findAll();
    }

    public UsuarioMissaoTO findByCodigo(Long codigo) {
        return UsuarioMissaoDAO.findByCodigo(codigo);
    }

    public UsuarioMissaoTO save(UsuarioMissaoTO usuarioMissao) {
        ajustarConclusao(usuarioMissao);
        return usuarioMissaoDAO.save(usuarioMissao);
    }

    public UsuarioMissaoTO update(UsuarioMissaoTO usuarioMissao) {
        ajustarConclusao(usuarioMissao);
        return usuarioMissaoDAO.update(usuarioMissao);
    }

    public boolean delete(Long codigo) {
        return usuarioMissaoDAO.delete(codigo);
    }

    private void ajustarConclusao(UsuarioMissaoTO usuarioMissao) {
        if (usuarioMissao.isConcluida() && usuarioMissao.getDataConclusao() == null) {
            usuarioMissao.setDataConclusao(LocalDate.now());
        } else if (!usuarioMissao.isConcluida()) {
            usuarioMissao.setDataConclusao(null);
        }
    }
}
