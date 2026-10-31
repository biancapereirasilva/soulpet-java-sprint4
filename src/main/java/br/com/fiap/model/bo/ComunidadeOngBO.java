package br.com.fiap.model.bo;

import br.com.fiap.model.dao.ComunidadeOngDAO;
import br.com.fiap.model.to.ComunidadeOngTO;

import java.util.ArrayList;

public class ComunidadeOngBO {
    private ComunidadeOngDAO comunidadeOngDAO = new ComunidadeOngDAO();

    public ArrayList<ComunidadeOngTO> findAll() {
        return comunidadeOngDAO.findAll();
    }

    public ComunidadeOngTO findByCodigo(Long codigo) {
        return ComunidadeOngDAO.findByCodigo(codigo);
    }

    public ComunidadeOngTO save(ComunidadeOngTO comunidadeOng) {
        return comunidadeOngDAO.save(comunidadeOng);
    }

    public ComunidadeOngTO update(ComunidadeOngTO comunidadeOng) {
        return comunidadeOngDAO.update(comunidadeOng);
    }

    public boolean delete(Long codigo) {
        return comunidadeOngDAO.delete(codigo);
    }
}
