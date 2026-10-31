package br.com.fiap.model.dao;

import br.com.fiap.model.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "select * from DDD_USUARIOS order by id_usuario";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setCodigo(rs.getLong("id_usuario"));
                    usuario.setNome(rs.getString("nm_usuario"));
                    usuario.setEmail(rs.getString("ds_email"));
                    usuario.setSenha(rs.getString("ds_senha"));
                    usuario.setTelefone(rs.getString("nr_telefone"));
                    usuario.setPontos(rs.getInt("qt_pontos"));
                    usuario.setNivel(rs.getString("tp_nivel"));
                    usuarios.add(usuario);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarios;
    }

    public static UsuarioTO findByCodigo(Long codigo) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "select * from DDD_USUARIOS where id_usuario = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setCodigo(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nm_usuario"));
                usuario.setEmail(rs.getString("ds_email"));
                usuario.setSenha(rs.getString("ds_senha"));
                usuario.setTelefone(rs.getString("nr_telefone"));
                usuario.setPontos(rs.getInt("qt_pontos"));
                usuario.setNivel(rs.getString("tp_nivel"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "insert into DDD_USUARIOS (nm_usuario, ds_email, ds_senha, nr_telefone, qt_pontos, tp_nivel) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection()
                .prepareStatement(sql, new String[]{"ID_USUARIO"})) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTelefone());
            ps.setInt(5, usuario.getPontos());
            ps.setString(6, usuario.getNivel());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setCodigo(rs.getLong(1));
                    }
                }
                return usuario;
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
        String sql = "delete from DDD_USUARIOS where id_usuario = ?";
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

    public UsuarioTO update(UsuarioTO usuario) {
        boolean trocaSenha = usuario.getSenha() != null && !usuario.getSenha().isBlank();
        String sql = trocaSenha
                ? "update DDD_USUARIOS set nm_usuario = ?, ds_email = ?, nr_telefone = ?, qt_pontos = ?, tp_nivel = ?, ds_senha = ? where id_usuario = ?"
                : "update DDD_USUARIOS set nm_usuario = ?, ds_email = ?, nr_telefone = ?, qt_pontos = ?, tp_nivel = ? where id_usuario = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTelefone());
            ps.setInt(4, usuario.getPontos());
            ps.setString(5, usuario.getNivel());
            if (trocaSenha) {
                ps.setString(6, usuario.getSenha());
                ps.setLong(7, usuario.getCodigo());
            } else {
                ps.setLong(6, usuario.getCodigo());
            }
            if (ps.executeUpdate() > 0) {
                return usuario;
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
