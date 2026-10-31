package br.com.fiap.model.dao;

import br.com.fiap.model.to.ComunidadeOngTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComunidadeOngDAO {

    public ArrayList<ComunidadeOngTO> findAll() {
        ArrayList<ComunidadeOngTO> comunidadeOngs = new ArrayList<>();
        String sql = "select * from DDD_COMUNIDADE_ONG order by id_comunidade";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ComunidadeOngTO comunidadeOng = new ComunidadeOngTO();
                    comunidadeOng.setCodigo(rs.getLong("id_comunidade"));
                    comunidadeOng.setNome(rs.getString("nm_comunidade"));
                    comunidadeOng.setDescricao(rs.getString("ds_comunidade"));
                    comunidadeOng.setContato(rs.getString("ds_contato"));
                    comunidadeOngs.add(comunidadeOng);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return comunidadeOngs;
    }

    public static ComunidadeOngTO findByCodigo(Long codigo) {
        ComunidadeOngTO comunidadeOng = new ComunidadeOngTO();
        String sql = "select * from DDD_COMUNIDADE_ONG where id_comunidade = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comunidadeOng.setCodigo(rs.getLong("id_comunidade"));
                comunidadeOng.setNome(rs.getString("nm_comunidade"));
                comunidadeOng.setDescricao(rs.getString("ds_comunidade"));
                comunidadeOng.setContato(rs.getString("ds_contato"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return comunidadeOng;
    }

    public ComunidadeOngTO save(ComunidadeOngTO comunidadeOng) {
        String sql = "insert into DDD_COMUNIDADE_ONG (nm_comunidade, ds_comunidade, ds_contato) values (?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection()
                .prepareStatement(sql, new String[]{"ID_COMUNIDADE"})) {
            ps.setString(1, comunidadeOng.getNome());
            ps.setString(2, comunidadeOng.getDescricao());
            ps.setString(3, comunidadeOng.getContato());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        comunidadeOng.setCodigo(rs.getLong(1));
                    }
                }
                return comunidadeOng;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "delete from DDD_COMUNIDADE_ONG where id_comunidade = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ComunidadeOngTO update(ComunidadeOngTO comunidadeOng) {
        String sql = "update DDD_COMUNIDADE_ONG set nm_comunidade = ?, ds_comunidade = ?, ds_contato = ? where id_comunidade = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, comunidadeOng.getNome());
            ps.setString(2, comunidadeOng.getDescricao());
            ps.setString(3, comunidadeOng.getContato());
            ps.setLong(4, comunidadeOng.getCodigo());
            if (ps.executeUpdate() > 0) {
                return comunidadeOng;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
