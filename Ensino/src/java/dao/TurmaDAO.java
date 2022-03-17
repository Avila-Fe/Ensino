
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
import modelo.Aluno;
import modelo.Turma;
import util.Conexao;

public class TurmaDAO {
    
    public static void inserir(Turma turma) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "insert into turma\n"
                + "(nomeTurma, capacidade, escolaFK, alunoFK)\n"
                + "values\n"
                + "(?, ?, ?, ?)";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, turma.getNomeTurma());
        stat.setInt(2, turma.getCapacidade());
        stat.setInt(3, turma.getEscola().getIdEscola());
        stat.setInt(4, turma.getAluno().getIdAluno());
        stat.execute();
        stat.close();
        con.close();
    }
    
    public static void alterar(Turma turma) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "update turma set nomeTurma = ?, capacidade = ?, escolaFK = ?, alunoFK = ?"
                +"WHERE idTurma = ?";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, turma.getNomeTurma());
        stat.setInt(2, turma.getCapacidade());
        stat.setInt(3, turma.getEscola().getIdEscola());
        stat.setInt(4, turma.getAluno().getIdAluno());
        stat.execute();
        stat.close();
        con.close();
    }
    
    public static void excluir(Turma turma) throws SQLException {
        Connection con = Conexao.getConexao();
        String sql = "delete from turma WHERE idTurma = ?";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setInt(1, turma.getIdTurma());
        stat.executeUpdate();
        stat.close();
        con.close();
    }
    
    public static List<Turma> getLista(int idEscola) throws SQLException {
        List<Turma> lista = new ArrayList<Turma>();
        Connection con = Conexao.getConexao();
        String sql = "SELECT\n"
                + "	*\n"
                + "FROM\n"
                 + "	turma t,\n"
                + "	aluno a,\n"
                + "	escola e\n"
                + "WHERE\n"
                + "	t.alunoFK = a.idAluno AND\n"
                + "     t.escolaFK = e.idEscola AND\n"
                + "     e.idEscola = ?\n"
                + "ORDER BY\n"
                + "	t.idTurma desc";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idEscola);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            
            Aluno a = new Aluno();
            a.setIdAluno(rs.getInt("idAluno"));
            a.setNome(rs.getString("nome"));
            a.setDataNasc(rs.getDate("dataNasc"));

            Escola e = new Escola();
            e.setIdEscola(rs.getInt("idEscola"));
            e.setNomeEscola(rs.getString("nomeEscola"));
            e.setLogradouro(rs.getString("logradouro"));
            e.setComplemento(rs.getString("complemento"));
            e.setBairro(rs.getString("bairro"));
            e.setCidade(rs.getString("cidade"));
            e.setEstado(rs.getString("estado"));
            
            Turma t = new Turma();
            t.setIdTurma(rs.getInt("idTurma"));
            t.setNomeTurma(rs.getString("nomeTurma"));
            t.setCapacidade(rs.getInt("capacidade"));
            t.setEscola(e);
            lista.add(t);
        }
        return lista;
    }
}
