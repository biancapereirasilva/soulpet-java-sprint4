package br.com.fiap.model.dao;

import br.com.fiap.model.to.UsuarioMissaoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UsuarioMissaoDAO {

    public ArrayList<UsuarioMissaoTO> findAll() {
        ArrayList<UsuarioMissaoTO> usuarioMissaos = new ArrayList<>();
        String sql = "select * from DDD_USUARIO_MISSAO order by id_usuario_missao";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioMissaoTO usuarioMissao = new UsuarioMissaoTO();
                    usuarioMissao.setCodigo(rs.getLong("id_usuario_missao"));
                    usuarioMissao.setConcluida("S".equals(rs.getString("st_concluida")));
                    Date dataConclusaoSql = rs.getDate("dt_conclusao");
                    usuarioMissao.setDataConclusao(dataConclusaoSql != null ? dataConclusaoSql.toLocalDate() : null);
                    usuarioMissao.setCodUsuario(rs.getLong("id_usuario"));
                    usuarioMissao.setCodMissao(rs.getLong("id_missao"));
                    usuarioMissaos.add(usuarioMissao);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarioMissaos;
    }

    public static UsuarioMissaoTO findByCodigo(Long codigo) {
        UsuarioMissaoTO usuarioMissao = new UsuarioMissaoTO();
        String sql = "select * from DDD_USUARIO_MISSAO where id_usuario_missao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioMissao.setCodigo(rs.getLong("id_usuario_missao"));
                usuarioMissao.setConcluida("S".equals(rs.getString("st_concluida")));
                Date dataConclusaoSql = rs.getDate("dt_conclusao");
                usuarioMissao.setDataConclusao(dataConclusaoSql != null ? dataConclusaoSql.toLocalDate() : null);
                usuarioMissao.setCodUsuario(rs.getLong("id_usuario"));
                usuarioMissao.setCodMissao(rs.getLong("id_missao"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarioMissao;
    }

    public UsuarioMissaoTO save(UsuarioMissaoTO usuarioMissao) {
        String sql = "insert into DDD_USUARIO_MISSAO (st_concluida, dt_conclusao, id_usuario, id_missao) values (?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection()
                .prepareStatement(sql, new String[]{"ID_USUARIO_MISSAO"})) {
            ps.setString(1, usuarioMissao.isConcluida() ? "S" : "N");
            if (usuarioMissao.getDataConclusao() != null) {
                ps.setDate(2, Date.valueOf(usuarioMissao.getDataConclusao()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            ps.setLong(3, usuarioMissao.getCodUsuario());
            ps.setLong(4, usuarioMissao.getCodMissao());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuarioMissao.setCodigo(rs.getLong(1));
                    }
                }
                return usuarioMissao;
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
        String sql = "delete from DDD_USUARIO_MISSAO where id_usuario_missao = ?";
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

    public UsuarioMissaoTO update(UsuarioMissaoTO usuarioMissao) {
        String sql = "update DDD_USUARIO_MISSAO set st_concluida = ?, dt_conclusao = ?, id_usuario = ?, id_missao = ? where id_usuario_missao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuarioMissao.isConcluida() ? "S" : "N");
            if (usuarioMissao.getDataConclusao() != null) {
                ps.setDate(2, Date.valueOf(usuarioMissao.getDataConclusao()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            ps.setLong(3, usuarioMissao.getCodUsuario());
            ps.setLong(4, usuarioMissao.getCodMissao());
            ps.setLong(5, usuarioMissao.getCodigo());
            if (ps.executeUpdate() > 0) {
                return usuarioMissao;
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
