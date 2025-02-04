package br.comvarejonline.projetoinicial.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "usuario_produto", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private Set<Produto> produtos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private Set<Grupo> grupos = new HashSet<>();

    public boolean senhaCoincideCom(String senha) {
        return getSenha().equals(senha);
    }

    public boolean senhaNaoCoincideCom(String senha) {
        return !senhaCoincideCom(senha);
    }

    public void removerGrupo(Grupo grupo) {
        getGrupos().remove(grupo);
    }

    public void adicionarGrupo(Grupo grupo) {
        getGrupos().add(grupo);
    }
     
    public void bcryptarSenha() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        senha = encoder.encode(getSenha());
    }
}
