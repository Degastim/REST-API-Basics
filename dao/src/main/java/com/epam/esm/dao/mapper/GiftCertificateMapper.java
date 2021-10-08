package com.epam.esm.dao.mapper;

import com.epam.esm.dao.constant.column.GiftCertificateColumnName;
import com.epam.esm.dao.constant.column.TagColumnName;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Gift certificates mapper.
 *
 * @author Yauheni Tstiov
 */
@Component
public class GiftCertificateMapper implements ResultSetExtractor<List<GiftCertificate>> {
    @Override
    public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        if (rs.next()) {
            while (!rs.isAfterLast()) {
                long giftCertificateId = rs.getLong(GiftCertificateColumnName.ID);
                String giftCertificateName = rs.getString(GiftCertificateColumnName.NAME);
                String description = rs.getString(GiftCertificateColumnName.DESCRIPTION);
                BigDecimal price = rs.getBigDecimal(GiftCertificateColumnName.PRICE);
                int duration = rs.getInt(GiftCertificateColumnName.DURATION);
                LocalDateTime createDate = rs.getObject(GiftCertificateColumnName.CREATE_DATE, LocalDateTime.class);
                LocalDateTime lastUpdateDate = rs.getObject(GiftCertificateColumnName.LAST_UPDATE_DATE, LocalDateTime.class);
                List<Tag> tags = new ArrayList<>();
                do {
                    long tagId = rs.getLong(TagColumnName.TAG_ID);
                    String tagName = rs.getString(TagColumnName.TAG_NAME);
                    if (tagId != 0 && tagName != null) {
                        tags.add(new Tag(tagId, tagName));
                    }
                } while (rs.next() && rs.getLong(GiftCertificateColumnName.ID) == giftCertificateId);
                giftCertificates.add(new GiftCertificate(giftCertificateId, giftCertificateName, description, price, duration, createDate, lastUpdateDate, tags));
            }
        }
        return giftCertificates;
    }
}
