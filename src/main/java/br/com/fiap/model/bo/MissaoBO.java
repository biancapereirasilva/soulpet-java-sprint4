package br.com.fiap.model.bo;

import br.com.fiap.model.dao.MissaoDAO;
import br.com.fiap.model.to.MissaoTO;

import java.util.ArrayList;

public class MissaoBO {

    private MissaoDAO missaoDAO;

    public ArrayList<MissaoTO> findAll() {
        missaoDAO = new MissaoDAO();
        return missaoDAO.findAll();
    }

    public MissaoTO findByCodigo(Long codigo) {
        missaoDAO = new MissaoDAO();
        return MissaoDAO.findByCodigo(codigo);
    }

    public MissaoTO save(MissaoTO missao) throws Exception {
        missaoDAO = new MissaoDAO();
        validarPontos(missao);
        return missaoDAO.save(missao);
    }

    public boolean delete(Long codigo) {
        missaoDAO = new MissaoDAO();
        return missaoDAO.delete(codigo);
    }

    public MissaoTO update(MissaoTO missao) throws Exception {
        missaoDAO = new MissaoDAO();
        validarPontos(missao);
        return missaoDAO.update(missao);
    }

    private void validarPontos(MissaoTO missao) throws Exception {
        if (missao.getPontosRecompensa() < 1 || missao.getPontosRecompensa() > 500) {
            throw new Exception("Os pontos da missão devem estar entre 1 e 500");
        }
    }
}
