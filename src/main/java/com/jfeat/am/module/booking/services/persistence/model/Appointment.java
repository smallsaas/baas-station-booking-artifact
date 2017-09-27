package com.jfeat.am.module.booking.services.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author J4cob
 * @since 2017-09-26
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
     * 客户姓名
     */
	@TableField("customer_id")
	private Long customerId;
    /**
     * 类型ID
     */
	@TableField("service_id")
	private Long serviceId;
    /**
     *  订单状态
     */
	private String status;
    /**
     * 预约费用
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
     *  完成(关闭)时间
     */
	@TableField("close_time")
	private Date closeTime;
    /**
     * 用户手机号码
     */
	private String phone;
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

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
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
		return "Appointment{" +
			"id=" + id +
			", studioId=" + studioId +
			", doctorId=" + doctorId +
			", customerId=" + customerId +
			", serviceId=" + serviceId +
			", status=" + status +
			", fee=" + fee +
			", createTime=" + createTime +
			", appointmentTime=" + appointmentTime +
			", closeTime=" + closeTime +
			", phone=" + phone +
			", fieldB=" + fieldB +
			", fieldC=" + fieldC +
			"}";
	}
}
