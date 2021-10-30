package com.epam.esm.mapper;

import com.epam.esm.dto.AbstractCustomDTO;
import com.epam.esm.entity.AbstractCustomEntity;

public interface DTOMapper<T extends AbstractCustomEntity, R extends AbstractCustomDTO> {
    /**
     * Maps a DTO as Entity.
     *
     * @param dto object object to map.
     * @return entity which contains the object after mapping.
     */
    T toEntity(R dto);

    /**
     * Maps an Entity asDTO .
     *
     * @param entity object object to map.
     * @return dto which contains the object after mapping.
     */
    R toDTO(T entity);
}
