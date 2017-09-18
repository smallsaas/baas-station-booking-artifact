package com.jfeat.am.module.booking.services.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
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
 * @since 2017-09-18
 */
@TableName("emb_studio")
public class Studio extends Model<Studio> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 工作室名称
     */
	private String name;
    /**
     * 工作室描述
     */
	private String description;
    /**
     * 工作时间
     */
	@TableField("work_time")
	private Date workTime;
    /**
     * 费用
     */
	private BigDecimal fee;
    /**
     * 地址
     */
	@TableField("studio_site")
	private String studioSite;
    /**
     * 头像
     */
	private String covers;
    /**
     * 保留字段
     */
	@TableField("field_a")
	private String fieldA;
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

	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getStudioSite() {
		return studioSite;
	}

	public void setStudioSite(String studioSite) {
		this.studioSite = studioSite;
	}

	public String getCovers() {
		return covers;
	}

	public void setCovers(String covers) {
		this.covers = covers;
	}

	public String getFieldA() {
		return fieldA;
	}

	public void setFieldA(String fieldA) {
		this.fieldA = fieldA;
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
		return "Studio{" +
			"id=" + id +
			", name=" + name +
			", description=" + description +
			", workTime=" + workTime +
			", fee=" + fee +
			", studioSite=" + studioSite +
			", covers=" + covers +
			", fieldA=" + fieldA +
			", fieldB=" + fieldB +
			", fieldC=" + fieldC +
			"}";
	}
}
