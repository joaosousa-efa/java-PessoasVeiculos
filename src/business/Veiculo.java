package business;

import java.util.Objects;

/**
 *
 * @author João Sousa
 * 
 */
public class Veiculo {
    
    private String marca;
    private String modelo;
    private String matricula;
    private int nChassi;
    private int cilindrada;
    private int nPortas;
    private String proprietario;


    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, String matricula, Integer nChassi, Integer cilindrada, Integer nPortas, String proprietario) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.nChassi = nChassi;
        this.cilindrada = cilindrada;
        this.nPortas = nPortas;
        this.proprietario = proprietario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getnChassi() {
        return nChassi;
    }

    public void setnChassi(Integer nChassi) {
        this.nChassi = nChassi;
    }

    public Integer getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Integer getnPortas() {
        return nPortas;
    }

    public void setnPortas(Integer nPortas) {
        this.nPortas = nPortas;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + ", nChassi=" + nChassi + ", cilindrada=" + cilindrada + ", nPortas=" + nPortas + ", proprietario=" + proprietario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.marca);
        hash = 19 * hash + Objects.hashCode(this.modelo);
        hash = 19 * hash + Objects.hashCode(this.matricula);
        hash = 19 * hash + Objects.hashCode(this.nChassi);
        hash = 19 * hash + Objects.hashCode(this.cilindrada);
        hash = 19 * hash + Objects.hashCode(this.nPortas);
        hash = 19 * hash + Objects.hashCode(this.proprietario);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.proprietario, other.proprietario)) {
            return false;
        }
        if (!Objects.equals(this.nChassi, other.nChassi)) {
            return false;
        }
        if (!Objects.equals(this.cilindrada, other.cilindrada)) {
            return false;
        }
        return Objects.equals(this.nPortas, other.nPortas);
    }

    
}
