package br.com.fiap.model.dao;

import br.com.fiap.model.to.MissaoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MissaoDAO {

    public ArrayList<MissaoTO> findAll() {
        ArrayList<MissaoTO> missoes = new ArrayList<>();
        String sql = "select * from DDD_MISSAO order by id_missao";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    MissaoTO missao = new MissaoTO();
                    missao.setCodigo(rs.getLong("id_missao"));
                    missao.setTitulo(rs.getString("nm_titulo"));
                    missao.setDescricao(rs.getString("ds_missao"));
                    missao.setPontosRecompensa(rs.getInt("qt_pontos_recompensa"));
                    missao.setConcluida("S".equals(rs.getString("st_concluida")));
                    missao.setCodComunidade(rs.getLong("id_comunidade"));
                    missoes.add(missao);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return missoes;
    }

    public static MissaoTO findByCodigo(Long codigo) {
        MissaoTO missao = new MissaoTO();
        String sql = "select * from DDD_MISSAO where id_missao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                missao.setCodigo(rs.getLong("id_missao"));
                missao.setTitulo(rs.getString("nm_titulo"));
                missao.setDescricao(rs.getString("ds_missao"));
                missao.setPontosRecompensa(rs.getInt("qt_pontos_recompensa"));
                missao.setConcluida("S".equals(rs.getString("st_concluida")));
                missao.setCodComunidade(rs.getLong("id_comunidade"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return missao;
    }

    public MissaoTO save(MissaoTO missao) {
        String sql = "insert into DDD_MISSAO (nm_titulo, ds_missao, qt_pontos_recompensa, st_concluida, id_comunidade) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, missao.getTitulo());
            ps.setString(2, missao.getDescricao());
            ps.setInt(3, missao.getPontosRecompensa());
            ps.setString(4, missao.isConcluida() ? "S" : "N");
            ps.setLong(5, missao.getCodComunidade());
            if (ps.executeUpdate() > 0) {
                return missao;
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
        String sql = "delete from DDD_MISSAO where id_missao = ?";
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

    public MissaoTO update(MissaoTO missao) {
        String sql = "update DDD_MISSAO set nm_titulo = ?, ds_missao = ?, qt_pontos_recompensa = ?, st_concluida = ?, id_comunidade = ? where id_missao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, missao.getTitulo());
            ps.setString(2, missao.getDescricao());
            ps.setInt(3, missao.getPontosRecompensa());
            ps.setString(4, missao.isConcluida() ? "S" : "N");
            ps.setLong(5, missao.getCodComunidade());
            ps.setLong(6, missao.getCodigo());
            if (ps.executeUpdate() > 0) {
                return missao;
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