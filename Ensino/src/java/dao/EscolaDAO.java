
package dao;

/**
 *
 * @author Ávila
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Escola;
import util.Conexao;

public class EscolaDAO {
    
    public static void inserir(Escola escola) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "insert into escola\n"
                + "(nomeEscola, logradouro, complemento, bairro, cidade, estado)\n"
                + "values\n"
                + "(?, ?, ?, ?, ?, ?)";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, escola.getNomeEscola());
        stat.setString(2, escola.getLogradouro());
        stat.setString(3, escola.getComplemento());
        stat.setString(4, escola.getBairro());
        stat.setString(5, escola.getCidade());
        stat.setString(6, escola.getEstado());
        stat.execute();
        stat.close();
        con.close();
    }
    
    public static void alterar(Escola escola) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "update turma set nomeEscola = ?, logradouro = ?,"
                +" complemento = ?, bairro = ?, cidade = ?,"
                +"estado = ? WHERE idEscola = ?";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, escola.getNomeEscola());
        stat.setString(2, escola.getLogradouro());
        stat.setString(3, escola.getComplemento());
        stat.setString(4, escola.getBairro());
        stat.setString(5, escola.getCidade());
        stat.setString(6, escola.getEstado());
        stat.setInt(7, escola.getIdEscola());
        stat.execute();
        stat.close();
        con.close();
    }
    
    public static void excluir(Escola escola) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "delete from escola WHERE idEscola = ?";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, escola.getIdEscola());
        stat.executeUpdate();
        stat.close();
        con.close();
    }
    
    public static List<Escola> getLista() throws SQLException {
        List<Escola> lista = new ArrayList<Escola>();
        Connection con = Conexao.getConexao();
        String sql = "select * from escola ORDER BY idEscola";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
                        
            
            Escola e = new Escola();
            e.setIdEscola(rs.getInt("idEscola"));
            e.setNomeEscola(rs.getString("nomeEscola")); 
            e.setLogradouro(rs.getString("logradouro"));
            e.setComplemento(rs.getString("complemento"));
            e.setBairro(rs.getString("bairro"));
            e.setCidade(rs.getString("cidade"));
            e.setEstado(rs.getString("estado"));
            lista.add(e);
        }
        return lista;
    }
}
