
package com.acc.vo;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class ProVideoQuery extends BaseQuery implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1726958302789590552L;

	private int id;
	
	private String videoUrl;

	private int memberId;
	
	private int status;

    private String createDate;

    private int createrId;

    private int provideoOrder;

    private String sortColumns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public int getProvideoOrder() {
        return provideoOrder;
    }

    public void setProvideoOrder(int provideoOrder) {
        this.provideoOrder = provideoOrder;
    }

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }
}

