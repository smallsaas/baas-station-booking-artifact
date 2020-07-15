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
 * @since 2017-10-16
 */
@TableName("emb_customer")
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
	/**
	 * 所属组织ID
	 */
	@TableField("orgId")
	private Long orgId;
	/**
	 * 组织标志
	 */
	@TableField("org_tag")
	private String orgTag;
    /**
     * 名称
     */
	private String name;
    /**
     * 电话
     */
	private String phone;
    /**
     * 生日
     */
	private Date birthday;
    /**
     * 认证状态
     */
	private Integer verified;
    /**
     *  性别
     */
	private String sex;
    /**
     * 微信
     */
	private String wechat;
    /**
     * 描述
     */
	private String description;
    /**
     * 头像
     */
	private String cover;
    /**
     * 经度
     */
	private BigDecimal longitude;
    /**
     * 纬度
     */
	private BigDecimal latitude;
    /**
     * 注册时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 微信提供openid
     */
	private String openid;
    /**
     * 保留字段
     */
	@TableField("user_id")
	private Long userId;


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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getVerified() {
		return verified;
	}

	public void setVerified(Integer verified) {
		this.verified = verified;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
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

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Customer{" +
			"id=" + id +
			", name=" + name +
			", phone=" + phone +
			", birthday=" + birthday +
			", verified=" + verified +
			", sex=" + sex +
			", wechat=" + wechat +
			", description=" + description +
			", cover=" + cover +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", createTime=" + createTime +
			", openid=" + openid +
			", userId=" + userId +
			"}";
	}
}
