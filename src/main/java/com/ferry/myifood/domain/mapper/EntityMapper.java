package com.ferry.myifood.domain.mapper;

import java.util.List;
import java.util.Set;

public interface EntityMapper<D, E> {

    /**
     * @param dto
     * @return E
     */
    E toEntity(D dto);

    /**
     * @param entity
     * @return D
     */
    D toDto(E entity);

    /**
     * @param dtoList
     * @return List<E>
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * @param entityList
     * @return List<D>
     */
    List<D> toDto(List<E> entityList);

    /**
     * @param entityList
     * @return Set<D>
     */
    Set<D> toDto(Set<E> entityList);
}
