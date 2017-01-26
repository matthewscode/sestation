package com.motionpoint.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Matthew on 1/25/2017.
 */
@Entity
@Table(name = "scope_analysis")
public class ScopeAnalysis {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    @NotNull
    @NotEmpty
    private String clientName;

    private String analyst;

    private String foundWords;

    private String foundImages;

    private String uniqueMeta;

    private String words;

    private String images;
    @Lob
    private String imageList;
    @Lob
    private String sitemapList;
    @Lob
    private String directoryList;
    @Lob
    private String directorySizeList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getFoundWords() {
        return foundWords;
    }

    public void setFoundWords(String foundWords) {
        this.foundWords = foundWords;
    }

    public String getFoundImages() {
        return foundImages;
    }

    public void setFoundImages(String foundImages) {
        this.foundImages = foundImages;
    }

    public String getUniqueMeta() {
        return uniqueMeta;
    }

    public void setUniqueMeta(String uniqueMeta) {
        this.uniqueMeta = uniqueMeta;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSitemapList() {
        return sitemapList;
    }

    public void setSitemapList(String sitemapList) {
        this.sitemapList = sitemapList;
    }

    public String getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(String directoryList) {
        this.directoryList = directoryList;
    }

    public String getDirectorySizeList() {
        return directorySizeList;
    }

    public void setDirectorySizeList(String directorySizeList) {
        this.directorySizeList = directorySizeList;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }
}
