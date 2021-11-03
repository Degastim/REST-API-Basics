package com.epam.esm.validator;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Validates TagCreationDTO.
 *
 * @author Yauheni Tsitov
 */
@Component
public class TagDTOValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private final TagDao tagDao;

    @Autowired
    public TagDTOValidator(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    /**
     * Is TagCreationDTO valid.
     *
     * @param tagDTO the object for validation.
     */
    public void isTagCreationDTOValid(TagDTO tagDTO) {
        long tagDTOId = tagDTO.getId();
        if (tagDTOId != 0) {
            throw new InvalidFieldValueException("Can't transfer id tag.", ExceptionCauseCode.TAG);
        }
        String tagName = tagDTO.getName();
        if (tagName != null) {
            if (tagDao.findByName(tagName).isPresent()) {
                throw new ResourceAlreadyExistException("There is already a tag with the same name.", ExceptionCauseCode.TAG);
            }

            if (!NAME_REGEX.matcher(tagName).matches()) {
                throw new InvalidFieldValueException("Tag name "+tagName+" is not valid", ExceptionCauseCode.TAG);
            }
        }else{
            throw new InvalidFieldValueException("Tag name null is not valid", ExceptionCauseCode.TAG);
        }
    }
}
