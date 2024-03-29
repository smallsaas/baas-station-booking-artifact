package com.jfeat.am.module.booking.services.persistence.model;

import java.io.Serializable;

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
 * @since 2017-10-16
 */
@TableName("emb_studio_service")
public class StudioService extends Model<StudioService> {

    private static final long serialVersionUID = 1L;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgTag() {
		return orgTag;
	}

	public void setOrgTag(String orgTag) {
		this.orgTag = orgTag;
	}

	/**
     * 主键
     */
	private Long id;
	/**
	 * 所属组织ID
	 */
	@TableField("org_id")
	private Long orgId;
	/**
	 * 组织标志
	 */
	@TableField("org_tag")
	private String orgTag;
    /**
     * 服务类型
     */
	@TableField("type_id")
	private Long typeId;
    /**
     * 技师ID
     */
	@TableField("doctor_id")
	private Long doctorId;
    /**
     * 工作室ID
     */
	@TableField("studio_id")
	private Long studioId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getStudioId() {
		return studioId;
	}

	public void setStudioId(Long studioId) {
		this.studioId = studioId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StudioService{" +
			"id=" + id +
			", typeId=" + typeId +
			", doctorId=" + doctorId +
			", studioId=" + studioId +
			"}";
	}
}
