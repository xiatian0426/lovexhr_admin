
package com.acc.vo;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class MommentQuery extends BaseQuery implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1750928302789590552L;

    private int id;

    /**
     * 评论人微信号
     */
    private String commentator_wechat;
    /**
     * 评论人昵称
     */
    private String commentator_name;
    /**
     * 评论人头像
     */
    private String commentator_img;

    /**
     * 评论内容
     */
    private String comment_context;
    /**
     * 评论时间
     */
    private String create_date;

    /**
     * 0：未删除 1：已删除
     */
    private int status;

    private String check_date;

    private int check_id;

    private int respondent_id;

    private int modifier_id;

    private String modify_date;

    private String comment_tag;

    private int star_level;

    private String sortColumns;

    public String getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        this.sortColumns = sortColumns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentator_wechat() {
        return commentator_wechat;
    }

    public void setCommentator_wechat(String commentator_wechat) {
        this.commentator_wechat = commentator_wechat;
    }

    public String getCommentator_name() {
        return commentator_name;
    }

    public void setCommentator_name(String commentator_name) {
        this.commentator_name = commentator_name;
    }

    public String getCommentator_img() {
        return commentator_img;
    }

    public void setCommentator_img(String commentator_img) {
        this.commentator_img = commentator_img;
    }

    public String getComment_context() {
        return comment_context;
    }

    public void setComment_context(String comment_context) {
        this.comment_context = comment_context;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public int getCheck_id() {
        return check_id;
    }

    public void setCheck_id(int check_id) {
        this.check_id = check_id;
    }

    public int getRespondent_id() {
        return respondent_id;
    }

    public void setRespondent_id(int respondent_id) {
        this.respondent_id = respondent_id;
    }

    public int getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(int modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }

    public String getComment_tag() {
        return comment_tag;
    }

    public void setComment_tag(String comment_tag) {
        this.comment_tag = comment_tag;
    }

    public int getStar_level() {
        return star_level;
    }

    public void setStar_level(int star_level) {
        this.star_level = star_level;
    }
}

