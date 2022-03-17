package controle;

/**
 *
 * @author Ávila
 */
import dao.AlunoDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Aluno;
import modelo.Turma;

@ManagedBean
@SessionScoped

public class AlunoControle {

    private List<Aluno> lista;
    private Aluno aluno = new Aluno();
    private boolean salvar = false;
    private int idTurma;
    private Turma turma;

    public String preparaIncluir() {
        aluno = new Aluno();
        salvar = true;
        return "cadastroAluno.xhtml?faces-redirect=true";

    }

    public String preparaAlterar() {
        salvar = false;
        return "cadastroAluno.xhtml?faces-redirect=true";

    }

    public String atualizaLista() {
        try {
            lista = AlunoDAO.getLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "manutencaoAluno.xhtml?faces-redirect=true";
    }
     
      public String salvar(){
        try {
            if(salvar){
                AlunoDAO.inserir(aluno);
            }else{
               AlunoDAO.alterar(aluno);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista();
        return "manutencaoAluno.xhtml?faces-redirect=true";
    }
      
      public void excluir(){
        try {
            AlunoDAO.excluir(aluno);
            atualizaLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Aluno> getLista() {
        return lista;
    }

    public void setLista(List<Aluno> lista) {
        this.lista = lista;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

}
