package br.com.fiap.model.dao;

import br.com.fiap.model.to.UsuarioRecompensaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UsuarioRecompensaDAO {

    public ArrayList<UsuarioRecompensaTO> findAll() {
        ArrayList<UsuarioRecompensaTO> usuarioRecompensas = new ArrayList<>();
        String sql = "select * from DDD_USUARIO_RECOMPENSA order by id_usuario_recompensa";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioRecompensaTO usuarioRecompensa = new UsuarioRecompensaTO();
                    usuarioRecompensa.setCodigo(rs.getLong("id_usuario_recompensa"));
                    Date dataResgateSql = rs.getDate("dt_resgate");
                    usuarioRecompensa.setDataResgate(dataResgateSql != null ? dataResgateSql.toLocalDate() : null);
                    usuarioRecompensa.setCodRecompensa(rs.getLong("id_recompensa"));
                    usuarioRecompensa.setCodUsuario(rs.getLong("id_usuario"));
                    usuarioRecompensas.add(usuarioRecompensa);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarioRecompensas;
    }

    public static UsuarioRecompensaTO findByCodigo(Long codigo) {
        UsuarioRecompensaTO usuarioRecompensa = new UsuarioRecompensaTO();
        String sql = "select * from DDD_USUARIO_RECOMPENSA where id_usuario_recompensa = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioRecompensa.setCodigo(rs.getLong("id_usuario_recompensa"));
                Date dataResgateSql = rs.getDate("dt_resgate");
                usuarioRecompensa.setDataResgate(dataResgateSql != null ? dataResgateSql.toLocalDate() : null);
                usuarioRecompensa.setCodRecompensa(rs.getLong("id_recompensa"));
                usuarioRecompensa.setCodUsuario(rs.getLong("id_usuario"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarioRecompensa;
    }

    public UsuarioRecompensaTO save(UsuarioRecompensaTO usuarioRecompensa) {
        String sql = "insert into DDD_USUARIO_RECOMPENSA (dt_resgate, id_recompensa, id_usuario) values (?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection()
                .prepareStatement(sql, new String[]{"ID_USUARIO_RECOMPENSA"})) {
            if (usuarioRecompensa.getDataResgate() != null) {
                ps.setDate(1, Date.valueOf(usuarioRecompensa.getDataResgate()));
            } else {
                ps.setNull(1, Types.DATE);
            }
            ps.setLong(2, usuarioRecompensa.getCodRecompensa());
            ps.setLong(3, usuarioRecompensa.getCodUsuario());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuarioRecompensa.setCodigo(rs.getLong(1));
                    }
                }
                return usuarioRecompensa;
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
        String sql = "delete from DDD_USUARIO_RECOMPENSA where id_usuario_recompensa = ?";
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

    public UsuarioRecompensaTO update(UsuarioRecompensaTO usuarioRecompensa) {
        String sql = "update DDD_USUARIO_RECOMPENSA set dt_resgate = ?, id_recompensa = ?, id_usuario = ? where id_usuario_recompensa = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            if (usuarioRecompensa.getDataResgate() != null) {
                ps.setDate(1, Date.valueOf(usuarioRecompensa.getDataResgate()));
            } else {
                ps.setNull(1, Types.DATE);
            }
            ps.setLong(2, usuarioRecompensa.getCodRecompensa());
            ps.setLong(3, usuarioRecompensa.getCodUsuario());
            ps.setLong(4, usuarioRecompensa.getCodigo());
            if (ps.executeUpdate() > 0) {
                return usuarioRecompensa;
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
