
package dao;
/**
 *
 * @author Ávila
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;
import modelo.Turma;
import util.Conexao;

public class AlunoDAO {
    

    public static void inserir(Aluno aluno) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "insert into aluno\n"
                + "(nome, dataNasc)\n"
                + "values\n"
                + "(?, ?)";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, aluno.getNome());
        stat.setDate(2, new Date(aluno.getDataNasc().getTime()));
        stat.execute();
        stat.close();
        con.close();
    }
    
    public static void alterar(Aluno aluno) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "update aluno set nome = ?, dataNasc = ? WHERE idAluno = ?";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, aluno.getNome());
        stat.setDate(2, new Date(aluno.getDataNasc().getTime()));
        stat.setInt(3, aluno.getTurma().getIdTurma());
        stat.execute();
        stat.close();
        con.close();
    }
    
    public static void excluir(Aluno aluno) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "delete from aluno WHERE idAluno = ?";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, aluno.getIdAluno());
        stat.executeUpdate();
        stat.close();
        con.close();
    }
    
    public static List<Aluno> getLista() throws SQLException {
        List<Aluno> lista = new ArrayList<Aluno>();
        Connection con = Conexao.getConexao();
        String sql = "select * from aluno a ORDER BY a.nome asc";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {          
            Aluno a = new Aluno();
            a.setIdAluno(rs.getInt("idAluno"));
            a.setNome(rs.getString("nome"));
            a.setDataNasc(rs.getDate("dataNasc"));
            lista.add(a);
        }
        return lista;
    }

    public static List<Aluno> getLista(int idTurma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
