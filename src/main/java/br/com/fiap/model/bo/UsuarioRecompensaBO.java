package br.com.fiap.model.bo;

import br.com.fiap.model.dao.UsuarioRecompensaDAO;
import br.com.fiap.model.to.UsuarioRecompensaTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioRecompensaBO {
    private UsuarioRecompensaDAO usuarioRecompensaDAO = new UsuarioRecompensaDAO();

    public ArrayList<UsuarioRecompensaTO> findAll() {
        return usuarioRecompensaDAO.findAll();
    }

    public UsuarioRecompensaTO findByCodigo(Long codigo) {
        return UsuarioRecompensaDAO.findByCodigo(codigo);
    }

    public UsuarioRecompensaTO save(UsuarioRecompensaTO usuarioRecompensa) {
        if (usuarioRecompensa.getDataResgate() == null) {
            usuarioRecompensa.setDataResgate(LocalDate.now());
        }
        return usuarioRecompensaDAO.save(usuarioRecompensa);
    }

    public UsuarioRecompensaTO update(UsuarioRecompensaTO usuarioRecompensa) {
        return usuarioRecompensaDAO.update(usuarioRecompensa);
    }

    public boolean delete(Long codigo) {
        return usuarioRecompensaDAO.delete(codigo);
    }
}
