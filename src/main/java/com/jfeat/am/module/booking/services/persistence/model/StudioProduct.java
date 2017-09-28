package com.jfeat.am.module.booking.services.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author J4cob
 * @since 2017-09-28
 */
@TableName("emb_studio_product")
public class StudioProduct extends Model<StudioProduct> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 工作室ID
     */
	@TableField("studio_id")
	private Long studioId;
    /**
     * 类型ID
     */
	private String feature;
    /**
     * 项目费用
     */
	private BigDecimal fee;
    /**
     *  项目名称
     */
	private String name;
    /**
     *  项目描述
     */
	private String description;
    /**
     *  封面
     */
	private String cover;
    /**
     * 属性/团购/次卡/精选
     */
	private String attribute;
    /**
     * 保留字段
     */
	@TableField("field_b")
	private String fieldB;
    /**
     * 保留字段
     */
	@TableField("field_c")
	private String fieldC;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudioId() {
		return studioId;
	}

	public void setStudioId(Long studioId) {
		this.studioId = studioId;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getFieldB() {
		return fieldB;
	}

	public void setFieldB(String fieldB) {
		this.fieldB = fieldB;
	}

	public String getFieldC() {
		return fieldC;
	}

	public void setFieldC(String fieldC) {
		this.fieldC = fieldC;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StudioProduct{" +
			"id=" + id +
			", studioId=" + studioId +
			", feature=" + feature +
			", fee=" + fee +
			", name=" + name +
			", description=" + description +
			", cover=" + cover +
			", attribute=" + attribute +
			", fieldB=" + fieldB +
			", fieldC=" + fieldC +
			"}";
	}
}
