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
@TableName("emb_products_photos")
public class ProductsPhotos extends Model<ProductsPhotos> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;

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
     * 产品ID
     */
	@TableField("product_id")
	private Long productId;
    /**
     * 产品图片
     */
	private String photo;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductsPhotos{" +
			"id=" + id +
			", productId=" + productId +
			", photo=" + photo +
			"}";
	}
}
