package com.epam.esm.dao.mapper;

import com.epam.esm.dao.constant.column.GiftCertificatesTagColumnName;
import com.epam.esm.entity.GiftCertificatesTag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GiftCertificatesTagMapper implements RowMapper<GiftCertificatesTag> {

    @Override
    public GiftCertificatesTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id=rs.getLong(GiftCertificatesTagColumnName.ID);
        long giftCertificateId=rs.getLong(GiftCertificatesTagColumnName.GIFT_CERTIFICATE_ID);
        long tagId=rs.getLong(GiftCertificatesTagColumnName.TAG_ID);
        return new GiftCertificatesTag(id,giftCertificateId,tagId);
    }
}
