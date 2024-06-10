package com.ferry.myifood.domain.repository.custom;

import com.ferry.myifood.domain.model.FotoProduto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProdutoRepositoryCustomImpl implements ProdutoRepositoryCustom {
    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public FotoProduto save(FotoProduto entity) {
        return manager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(FotoProduto entity) {
        manager.remove(entity);
    }
}
