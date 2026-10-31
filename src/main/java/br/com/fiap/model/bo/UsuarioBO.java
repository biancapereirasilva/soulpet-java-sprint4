package br.com.fiap.model.bo;

import br.com.fiap.model.dao.UsuarioDAO;
import br.com.fiap.model.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    public UsuarioTO findByCodigo(Long codigo) {
        usuarioDAO = new UsuarioDAO();
        return UsuarioDAO.findByCodigo(codigo);
    }

    public UsuarioTO save(UsuarioTO usuario) throws Exception {
        usuarioDAO = new UsuarioDAO();
        validar(usuario);
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new Exception("A senha é obrigatória");
        }
        return usuarioDAO.save(usuario);
    }

    public boolean delete(Long codigo) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(codigo);
    }

    public UsuarioTO update(UsuarioTO usuario) throws Exception {
        usuarioDAO = new UsuarioDAO();
        validar(usuario);
        return usuarioDAO.update(usuario);
    }

    private void validar(UsuarioTO usuario) throws Exception {
        if (usuario.getPontos() < 0) {
            throw new Exception("Os pontos do usuário não podem ser negativos");
        }
    }
}