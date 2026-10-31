package br.com.fiap.model.bo;

import br.com.fiap.model.dao.RecompensaDAO;
import br.com.fiap.model.to.RecompensaTO;

import java.util.ArrayList;

public class RecompensaBO {

    private RecompensaDAO recompensaDAO;

    public ArrayList<RecompensaTO> findAll() {
        recompensaDAO = new RecompensaDAO();
        return recompensaDAO.findAll();
    }

    public RecompensaTO findByCodigo(Long codigo) {
        recompensaDAO = new RecompensaDAO();
        return RecompensaDAO.findByCodigo(codigo);
    }

    public RecompensaTO save(RecompensaTO recompensa) throws Exception {
        recompensaDAO = new RecompensaDAO();
        validar(recompensa);
        return recompensaDAO.save(recompensa);
    }

    public boolean delete(Long codigo) {
        recompensaDAO = new RecompensaDAO();
        return recompensaDAO.delete(codigo);
    }

    public RecompensaTO update(RecompensaTO recompensa) throws Exception {
        recompensaDAO = new RecompensaDAO();
        validar(recompensa);
        return recompensaDAO.update(recompensa);
    }

    private void validar(RecompensaTO recompensa) throws Exception {
        if (recompensa.getCustoPontos() <= 0) {
            throw new Exception("O custo em pontos deve ser maior que zero");
        }
    }
}