package com.epam.esm.dto;

public class ParamContainer {
    private String tagName;
    private String partGiftCertificateName;
    private String partGiftCertificateDescription;
    private String sortByName;
    private String sortByCreateDate;
    private String sortByLastUpdateDate;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getPartGiftCertificateName() {
        return partGiftCertificateName;
    }

    public void setPartGiftCertificateName(String partGiftCertificateName) {
        this.partGiftCertificateName = partGiftCertificateName;
    }

    public String getPartGiftCertificateDescription() {
        return partGiftCertificateDescription;
    }

    public void setPartGiftCertificateDescription(String partGiftCertificateDescription) {
        this.partGiftCertificateDescription = partGiftCertificateDescription;
    }

    public String getSortByName() {
        return sortByName;
    }

    public void setSortByName(String sortByName) {
        this.sortByName = sortByName;
    }

    public String getSortByCreateDate() {
        return sortByCreateDate;
    }

    public void setSortByCreateDate(String sortByCreateDate) {
        this.sortByCreateDate = sortByCreateDate;
    }

    public String getSortByLastUpdateDate() {
        return sortByLastUpdateDate;
    }

    public void setSortByLastUpdateDate(String sortByLastUpdateDate) {
        this.sortByLastUpdateDate = sortByLastUpdateDate;
    }
}
