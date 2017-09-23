package com.jfeat.am.module.booking.services.persistence.model;

import java.io.Serializable;

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
 * @since 2017-09-22
 */
@TableName("emb_doctor")
public class Doctor extends Model<Doctor> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 技师姓名
     */
	private String name;
    /**
     *  性别
     */
	private String sex;
    /**
     * 工作室ID
     */
	@TableField("studio_id")
	private Long studioId;
    /**
     * 自我描述
     */
	private String description;
    /**
     *  工作时间
     */
	@TableField("work_time")
	private Date workTime;
    /**
     * 头像
     */
	private String cover;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getStudioId() {
		return studioId;
	}

	public void setStudioId(Long studioId) {
		this.studioId = studioId;
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
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
		return "Doctor{" +
			"id=" + id +
			", name=" + name +
			", sex=" + sex +
			", studioId=" + studioId +
			", description=" + description +
			", workTime=" + workTime +
			", cover=" + cover +
			", fieldA=" + fieldA +
			", fieldB=" + fieldB +
			", fieldC=" + fieldC +
			"}";
	}
}
