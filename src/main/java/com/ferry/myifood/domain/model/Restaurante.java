package com.ferry.myifood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo = Boolean.TRUE;
    private Boolean aberto = Boolean.TRUE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @Embedded
    private Endereco endereco;

    @ManyToOne
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(name = "restaurante_produto",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private Set<Produto> produtos = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurante_formas_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "formas_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurante_responsaveis",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "responsavel_id"))
    private Set<Usuario> responsaveis = new LinkedHashSet<>();

    public void adicionaFormaPagamento(FormaPagamento formaPagamento) {
        getFormasPagamento().add(formaPagamento);
    }

    public void removeFormaPagamento(FormaPagamento formaPagamento) {
        getFormasPagamento().remove(formaPagamento);
    }

    public void adicionaProduto(Produto produto) {
        getProdutos().add(produto);
    }

    public void removeProduto(Produto produto) {
        getProdutos().remove(produto);
    }

    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public void abrir() {
        setAberto(true);
    }

    public void fechar() {
        setAberto(false);
    }

    public void adicionaUsuario(Usuario usuario) {
        getResponsaveis().add(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        getResponsaveis().remove(usuario);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Restaurante that = (Restaurante) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
