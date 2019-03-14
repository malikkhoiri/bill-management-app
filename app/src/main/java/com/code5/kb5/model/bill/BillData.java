package com.code5.kb5.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BillData implements Serializable {
    @SerializedName("id_tag")
    @Expose
    private Integer idTag;
    @SerializedName("nama_tag")
    @Expose
    private String namaTag;
    @SerializedName("total_tag")
    @Expose
    private Integer totalTag;
    @SerializedName("description_tag")
    @Expose
    private String descriptionTag;
    @SerializedName("status_tag")
    @Expose
    private String statusTag;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public String getNamaTag() {
        return namaTag;
    }

    public void setNamaTag(String namaTag) {
        this.namaTag = namaTag;
    }

    public Integer getTotalTag() {
        return totalTag;
    }

    public void setTotalTag(Integer totalTag) {
        this.totalTag = totalTag;
    }

    public String getDescriptionTag() {
        return descriptionTag;
    }

    public void setDescriptionTag(String descriptionTag) {
        this.descriptionTag = descriptionTag;
    }

    public String getStatusTag() {
        return statusTag;
    }

    public void setStatusTag(String statusTag) {
        this.statusTag = statusTag;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
