package br.com.fiap.model.dao;

import br.com.fiap.model.to.RecompensaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecompensaDAO {

    public ArrayList<RecompensaTO> findAll() {
        ArrayList<RecompensaTO> recompensas = new ArrayList<>();
        String sql = "select * from DDD_RECOMPENSAS order by id_recompensa";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    RecompensaTO recompensa = new RecompensaTO();
                    recompensa.setCodigo(rs.getLong("id_recompensa"));
                    recompensa.setNome(rs.getString("nm_recompensa"));
                    recompensa.setDescricao(rs.getString("ds_recompensa"));
                    recompensa.setCustoPontos(rs.getInt("qt_custo_pontos"));
                    recompensa.setTipo(rs.getString("tp_recompensa"));
                    recompensa.setEstoque(rs.getInt("qt_estoque"));
                    recompensas.add(recompensa);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return recompensas;
    }

    public static RecompensaTO findByCodigo(Long codigo) {
        RecompensaTO recompensa = new RecompensaTO();
        String sql = "select * from DDD_RECOMPENSAS where id_recompensa = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recompensa.setCodigo(rs.getLong("id_recompensa"));
                recompensa.setNome(rs.getString("nm_recompensa"));
                recompensa.setDescricao(rs.getString("ds_recompensa"));
                recompensa.setCustoPontos(rs.getInt("qt_custo_pontos"));
                recompensa.setTipo(rs.getString("tp_recompensa"));
                recompensa.setEstoque(rs.getInt("qt_estoque"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return recompensa;
    }

    public RecompensaTO save(RecompensaTO recompensa) {
        String sql = "insert into DDD_RECOMPENSAS (nm_recompensa, ds_recompensa, qt_custo_pontos, tp_recompensa, qt_estoque) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection()
                .prepareStatement(sql, new String[]{"ID_RECOMPENSA"})) {
            ps.setString(1, recompensa.getNome());
            ps.setString(2, recompensa.getDescricao());
            ps.setInt(3, recompensa.getCustoPontos());
            ps.setString(4, recompensa.getTipo());
            ps.setInt(5, recompensa.getEstoque());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        recompensa.setCodigo(rs.getLong(1));
                    }
                }
                return recompensa;
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
        String sql = "delete from DDD_RECOMPENSAS where id_recompensa = ?";
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

    public RecompensaTO update(RecompensaTO recompensa) {
        String sql = "update DDD_RECOMPENSAS set nm_recompensa = ?, ds_recompensa = ?, qt_custo_pontos = ?, tp_recompensa = ?, qt_estoque = ? where id_recompensa = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, recompensa.getNome());
            ps.setString(2, recompensa.getDescricao());
            ps.setInt(3, recompensa.getCustoPontos());
            ps.setString(4, recompensa.getTipo());
            ps.setInt(5, recompensa.getEstoque());
            ps.setLong(6, recompensa.getCodigo());
            if (ps.executeUpdate() > 0) {
                return recompensa;
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
