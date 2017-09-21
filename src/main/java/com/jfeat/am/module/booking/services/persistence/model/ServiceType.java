package com.jfeat.am.module.booking.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author J4cob
 * @since 2017-09-21
 */
@TableName("emb_service_type")
public class ServiceType extends Model<ServiceType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 类型名称
     */
	private String name;
    /**
     * 外键
     */
	private Long pid;


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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ServiceType{" +
			"id=" + id +
			", name=" + name +
			", pid=" + pid +
			"}";
	}
}
