package br.com.fiap.hackathon.spring.entity;

import br.com.fiap.hackathon.core.exception.BusinessException;
import br.com.fiap.hackathon.core.vo.cliente.ClienteVo;
import br.com.fiap.hackathon.core.vo.cliente.EnderecoVo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @Column(name = "cd_cpf", nullable = false, unique = true, length = 11)
    String cpf;
    @Column(name = "nm_cliente", nullable = false)
    String nome;
    @Column(name = "ds_email", nullable = false)
    String email;
    @Column(name = "nr_telefone", nullable = false)
    String telefone;
    @Column(name = "nm_rua", nullable = false)
    String rua;
    @Column(name = "nm_cidade", nullable = false)
    String cidade;
    @Column(name = "nm_estado", nullable = false)
    String estado;
    @Column(name = "cd_cep", nullable = false)
    String cep;
    @Column(name = "nm_pais", nullable = false)
    String pais;
 
    public ClienteEntity() {}

    public ClienteEntity(ClienteVo cliente) {
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.email =  cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.rua = cliente.getEndereco().getRua();
        this.cidade = cliente.getEndereco().getCidade();
        this.estado = cliente.getEndereco().getEstado();
        this.cep = cliente.getEndereco().getCep();
        this.pais = cliente.getEndereco().getPais();
    }

    public ClienteVo toVo() throws BusinessException{
        final EnderecoVo enderecoVo = new EnderecoVo(rua, cidade, estado, cep, pais);
        return new ClienteVo(cpf, nome, email, telefone, enderecoVo);
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
}
