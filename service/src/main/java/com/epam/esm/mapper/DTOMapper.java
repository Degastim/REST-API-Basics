package com.epam.esm.mapper;

import com.epam.esm.dto.AbstractDTO;
import com.epam.esm.entity.AbstractEntity;

public interface DTOMapper<T extends AbstractEntity, R extends AbstractDTO> {
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
