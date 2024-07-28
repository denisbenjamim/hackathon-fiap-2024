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

   
}
