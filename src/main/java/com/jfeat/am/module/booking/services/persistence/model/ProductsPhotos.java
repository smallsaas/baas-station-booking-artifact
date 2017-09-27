package com.jfeat.am.module.booking.services.persistence.model;

import java.io.Serializable;

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
@TableName("emb_products_photos")
public class ProductsPhotos extends Model<ProductsPhotos> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 公司名字
     */
	@TableField("product_id")
	private Long productId;
    /**
     * 图片
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
