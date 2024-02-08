package business;

import exceptions.VeiculoNaoExiste;
import java.sql.SQLException;
import java.util.Hashtable;
import persistance.DBWorker;

/**
 *
 * @author Joao Sousa
 */
public class GestorVeiculos {

    DBWorker dbw;

    public GestorVeiculos() throws SQLException {
        dbw = new DBWorker();
    }
    
    Hashtable<String, Veiculo> veiculos = new Hashtable<String, Veiculo>();

    public Veiculo getVeiculo(String matricula) {
        return veiculos.get(matricula);
    }

    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        
        dbw.guardarVeiculo(veiculo);
        veiculos.put(veiculo.getMatricula(), veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) throws VeiculoNaoExiste, SQLException {
        
        if (veiculos.remove(veiculo.getMatricula()) == null){
            throw new VeiculoNaoExiste("O veiculo não existe.");
        } else{
            dbw.removerVeiculo(veiculo.getMatricula());
            veiculos.remove(veiculo.getMatricula());
        }
    }

    public void removerVeiculo(String matricula) throws VeiculoNaoExiste{
        
        if (veiculos.remove(matricula) == null) {
            throw new VeiculoNaoExiste("O veículo não existe.");
        } else {
            veiculos.remove(matricula);
        }
    }

    public boolean contains(Veiculo veiculo) {
        return veiculos.contains(veiculo.getMatricula());
    }

    public Hashtable<String, Veiculo> listaVeiculos() {
        return veiculos;
    }

    @Override
    public String toString() {
        return "GestorVeiculos{" + "veiculos=" + veiculos + '}';
    }
}
