package controle;

/**
 *
 * @author Ávila
 */
import dao.TurmaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Turma;
import modelo.Aluno;
import modelo.Escola;

@ManagedBean
@SessionScoped

public class TurmaControle {

    private List<Turma> lista;
    private Turma turma = new Turma();
    private boolean salvar = false;
    private int idEscola;
    private int idAluno;
    private Escola escola;

    public String preparaIncluir() {
        turma = new Turma();
        salvar = true;
        idAluno = 0;
        idEscola = escola.getIdEscola();
        return "cadastroTurma.xhtml?faces-redirect=true";

    }

    public String preparaAlterar() {
        salvar = false;
        idAluno = turma.getAluno().getIdAluno();
        idEscola = turma.getEscola().getIdEscola();
        return "cadastroTurma.xhtml?faces-redirect=true";

    }

    public String atualizaLista(Escola escola) {
        try {
            System.out.println(escola.getIdEscola());
            setEscola(escola);
            lista = TurmaDAO.getLista(escola.getIdEscola());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "manutencaoTurma.xhtml?faces-redirect=true";
    }

    public String salvar() {
        Aluno aluno = new Aluno();
        aluno.setIdAluno(idAluno);
        turma.setAluno(aluno);
       
        Escola escola = new Escola();
        escola.setIdEscola(idEscola);
        turma.setEscola(escola);
        
        try {
            if (salvar) {
                TurmaDAO.inserir(turma);
            } else {
                TurmaDAO.alterar(turma);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista(escola);
        return "manutencaoTurma.xhtml?faces-redirect=true";
    }

    public void excluir() {
        try {
            TurmaDAO.excluir(turma);
            atualizaLista(escola);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Turma> getLista() {
        return lista;
    }

    public void setLista(List<Turma> lista) {
        this.lista = lista;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

    public int getIdEscola() {
        return idEscola;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public void setIdEscola(int idEscola) {
        this.idEscola = idEscola;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

}
