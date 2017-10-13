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
 * @since 2017-10-13
 */
@TableName("emb_appointment")
public class Appointment extends Model<Appointment> {

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
     * 技师ID
     */
	@TableField("doctor_id")
	private Long doctorId;
    /**
     * 用户ID
     */
	@TableField("customer_id")
	private Long customerId;
    /**
     *  状态
     */
	private String status;
    /**
     * 费用
     */
	private BigDecimal fee;
    /**
     *  创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     *  预约时间
     */
	@TableField("appointment_time")
	private Date appointmentTime;
    /**
     *  结束时间
     */
	@TableField("close_time")
	private Date closeTime;
    /**
     * 电话
     */
	private String phone;
    /**
     * 保留字段
     */
	@TableField("customer_name")
	private String customerName;
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

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
		return "Appointment{" +
			"id=" + id +
			", studioId=" + studioId +
			", doctorId=" + doctorId +
			", customerId=" + customerId +
			", status=" + status +
			", fee=" + fee +
			", createTime=" + createTime +
			", appointmentTime=" + appointmentTime +
			", closeTime=" + closeTime +
			", phone=" + phone +
			", customerName=" + customerName +
			", fieldC=" + fieldC +
			"}";
	}
}
