package controle;

/**
 *
 * @author Ávila
 */
import dao.EscolaDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Escola;

@ManagedBean
@SessionScoped

public class EscolaControle {

    private List<Escola> lista;
    private Escola escola = new Escola();
    private boolean salvar = false;
    private int idEscola;

    public String preparaIncluir() {
        escola = new Escola();
        salvar = true;
        return "cadastroEscola.xhtml?faces-redirect=true";
    }
    
    public String preparaAlterar() {
        salvar = false;
        return "cadastroEscola.xhtml?faces-redirect=true";

    }
    @PostConstruct
    public void atualizaLista (){
        try {
            lista = EscolaDAO.getLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public String salvar(){           
        try {
            if(salvar){
                EscolaDAO.inserir(escola);
            }else{
               EscolaDAO.alterar(escola);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        atualizaLista();
        return "manutencaoEscola.xhtml?faces-redirect=true";
    }
     public void excluir(){
        try {
            EscolaDAO.excluir(escola);
            atualizaLista();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Escola> getLista() {
        return lista;
    }

    public void setLista(List<Escola> lista) {
        this.lista = lista;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public boolean isSalvar() {
        return salvar;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }
     
}
