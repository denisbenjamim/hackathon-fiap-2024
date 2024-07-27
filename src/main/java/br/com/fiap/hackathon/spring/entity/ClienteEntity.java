package br.com.fiap.hackathon.spring.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_cliente")
    UUID codigo;
    @Column(name = "cd_cpf")
    String cpf;
    @Column(name = "nm_cliente")
    String nome;
    @Column(name = "ds_email")
    String email;
    @Column(name = "nr_telefone")
    String telefone;
    @Column(name = "nm_rua")
    String rua;
    @Column(name = "nm_cidade")
    String cidade;
    @Column(name = "nm_estado")
    String estado;
    @Column(name = "cd_cep")
    String cep;
    @Column(name = "nm_pais")
    String pais;

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
    public UUID getCodigo() {
        return codigo;
    }
    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

}
