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
 * @since 2017-09-15
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
			"}";
	}
}
