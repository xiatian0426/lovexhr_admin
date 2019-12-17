
package com.acc.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BxQA implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756758302889590552L;

	private int id;
	
	private String ask;

	private String answer;
	
	private int memberId;
	
	private int status;

    private String createDate;

    private int createrId;

    private int qaOrder;
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

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public int getQaOrder() {
        return qaOrder;
    }

    public void setQaOrder(int qaOrder) {
        this.qaOrder = qaOrder;
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

