
package com.acc.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxRecruit implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756958302889590552L;

	private int id;
	
	private String imageUrl;

	private int memberId;
	
	private int status;

    private String createDate;

    private int createrId;

    private int recruitOrder;

    /**
     * modifyDate       db_column: MODIFY_DATE
     */
    private String modifyDate;
    /**
     * modifierId       db_column: MODIFIER_ID
     */
    private String modifierId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreaterId() {
        return createrId;
    }

    public void setCreaterId(int createrId) {
        this.createrId = createrId;
    }

    public int getRecruitOrder() {
        return recruitOrder;
    }

    public void setRecruitOrder(int recruitOrder) {
        this.recruitOrder = recruitOrder;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }
}

