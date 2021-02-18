package challenge.man.monitortruck.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Base mapper.
 *
 * @param <E> the type parameter
 * @param <D> the type parameter
 */
public abstract class BaseMapper<E, D> {

    /**
     * To dto d.
     *
     * @param entity the entity
     * @return the dto
     */
    public abstract D toDto(E entity);

    /**
     * To entity e.
     *
     * @param dto the dto
     * @return the entity
     */
    public abstract E toEntity(D dto);

    /**
     * To dto list.
     *
     * @param entityList the entity list
     * @return the dto list
     */
    public List<D> toDtoList (List<E> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * To entity list.
     *
     * @param dtoList the dto list
     * @return the entity list
     */
    public List<E> toEntityList (List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
