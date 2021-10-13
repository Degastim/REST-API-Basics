package com.epam.esm.validator;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.tag.TagCreationDTO;
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
public class TagCreationDTOValidator {
    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private final TagDao tagDao;

    @Autowired
    private TagCreationDTOValidator(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    /**
     * Is name TagCreationDTO valid.
     *
     * @param tagCreationDTO the object for validation.
     */
    public void isTagCreationDTOValid(TagCreationDTO tagCreationDTO) {
        long tagId = tagCreationDTO.getId();
        String tagName = tagCreationDTO.getName();
        if (tagId != 0 && tagDao.findById(tagId).isPresent()) {
            throw new ResourceAlreadyExistException("There is already a tag with the same id.", ExceptionCauseCode.TAG);
        }
        if (tagDao.findByName(tagName).isPresent()) {
            throw new ResourceAlreadyExistException("There is already a tag with the same name.", ExceptionCauseCode.TAG);
        }
        if (!NAME_REGEX.matcher(tagCreationDTO.getName()).matches()) {
            throw new InvalidFieldValueException("Tag name is not valid", ExceptionCauseCode.TAG);
        }
    }
}
