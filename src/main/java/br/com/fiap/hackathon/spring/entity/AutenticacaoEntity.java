package br.com.fiap.hackathon.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_autenticacao")
public class AutenticacaoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer codigo;

    @Column(name = "cd_login", length = 10)
    String login;
    @Column(name = "cd_hash_password")
    String password;
    
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
