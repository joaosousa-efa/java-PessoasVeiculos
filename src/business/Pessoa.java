package business;

import exceptions.IdadeException;
import exceptions.LimiteDeVeiculosException;
import exceptions.NomeInvalidoException;
import exceptions.NumeroCCException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author João Sousa
 *
 */
public class Pessoa {

    private String nome;
    private String numeroCC;
    private Integer idade;
    private ArrayList<String> contactos = new ArrayList<>();
    private ArrayList<Veiculo> veiculos = new ArrayList<>();

    public Pessoa() {
    }
    
    public Pessoa(String nome, String numeroCC, Integer idade) {
        this.nome = nome;
        this.numeroCC = numeroCC;
        this.idade = idade;
    }

    public Pessoa(String nome, String numeroCC, Integer idade, ArrayList<Veiculo> veiculos) {
        this.nome = nome;
        this.numeroCC = numeroCC;
        this.idade = idade;
        this.veiculos = veiculos;
    }

    // NOME
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeInvalidoException {
        if (nome.length() == 0) {
            throw new NomeInvalidoException("O nome não pode ser vazio.");
        }

        if (nome.length() < 6) {
            throw new NomeInvalidoException("O nome tem de possuir 6 ou mais caracteres.");
        }
        this.nome = nome;
    }

    // CARTAO CIDADAO
    public String getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(String numeroCC) throws NumeroCCException{
        
        if (numeroCC.length() < 9 ) {
            throw new NumeroCCException("O cartão do cidadão tem de ter pelo menos 9 digitos.");
        }
        this.numeroCC = numeroCC;
    }
    
    // IDADE

    /**
     *
     * @return
     */
    public int getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) throws IdadeException {

        if ( idade < 1 ) {
            throw new IdadeException("A idade não pode ser inferior a zero.");
        }
        
        this.idade = idade;
    }

    // CONTACTOS
    public boolean adicionarContacto(String contacto) {
        return contactos.add(contacto);
    }

    public boolean removerContacto(String contacto) {
        return contactos.remove(contacto);
    }
    
    // VEICULOS
    public void adicionarVeiculo(Veiculo veiculo) throws LimiteDeVeiculosException{
        
        if(veiculos.size() >= 3){
            throw new LimiteDeVeiculosException("Cada pessoa só pode ter no máximo 3 veículos.");
        }
        veiculos.add(veiculo);
    }
    
    public boolean removerVeiculo(Veiculo veiculo){
        
        return veiculos.remove(veiculo);
    }
    
    public ArrayList<Veiculo> listaVeiculos(){
        return veiculos;
    }
    
    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", numeroCC=" + numeroCC + ", idade=" + idade + ", contactos=" + contactos + ", veiculos=" + veiculos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + Objects.hashCode(this.numeroCC);
        hash = 61 * hash + Objects.hashCode(this.idade);
        hash = 61 * hash + Objects.hashCode(this.contactos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.numeroCC, other.numeroCC)) {
            return false;
        }
        if (!Objects.equals(this.idade, other.idade)) {
            return false;
        }
        return Objects.equals(this.contactos, other.contactos);
    } 
}
