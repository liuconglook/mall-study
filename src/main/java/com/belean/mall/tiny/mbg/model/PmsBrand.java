package com.belean.mall.tiny.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.hibernate.validator.constraints.Length;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
* 品牌表
* @TableName pms_brand
*/
@TableName(value = "pms_brand")
@Schema(name = "品牌类", description = "品牌表", title = "品牌")
@Getter
@Setter
@ToString
public class PmsBrand implements Serializable {

    /**
    * 
    */
    @TableId(value = "id", type = IdType.NONE)
    @Schema(description = "")
    private Long id;
    /**
    * 品牌名称
    */
    @Schema(description = "品牌名称")
    @Length(max= 64,message="编码长度不能超过64")
    private String name;
    /**
    * 首字母
    */
    @Schema(description = "首字母")
    @Length(max= 8,message="编码长度不能超过8")
    private String first_letter;
    /**
    * 
    */
    @Schema(description = "")
    private Integer sort;
    /**
    * 是否为品牌制造商：0->不是；1->是
    */
    @Schema(description = "是否为品牌制造商：0->不是；1->是")
    private Integer factory_status;
    /**
    * 
    */
    @Schema(description = "")
    private Integer show_status;
    /**
    * 产品数量
    */
    @Schema(description = "产品数量")
    private Integer product_count;
    /**
    * 产品评论数量
    */
    @Schema(description = "产品评论数量")
    private Integer product_comment_count;
    /**
    * 品牌logo
    */
    @Schema(description = "品牌logo")
    @Length(max= 255,message="编码长度不能超过255")
    private String logo;
    /**
    * 专区大图
    */
    @Schema(description = "专区大图")
    @Length(max= 255,message="编码长度不能超过255")
    private String big_pic;
    /**
    * 品牌故事
    */
    @Schema(description = "品牌故事")
    @Length(max= -1,message="编码长度不能超过-1")
    private String brand_story;
}
