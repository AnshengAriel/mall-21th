package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import com.cskaoyan.mall.utils.TypeConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private Integer id;

    private String title;

    private String subtitle;

    private BigDecimal price;

    private String readCount;

    private String picUrl;

    private Integer sortOrder;

    private String goods;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private String content;

    public Topic(TopicWithGoods topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.subtitle = topic.getSubtitle();
        this.price = topic.getPrice();
        this.readCount = topic.getReadCount();
        this.picUrl = topic.getPicUrl();
        this.sortOrder = topic.getSortOrder();
        this.goods = TypeConverter.parse(topic.getGoods());
        this.addTime = topic.getAddTime();
        this.updateTime = topic.getUpdateTime();
        this.deleted = topic.getDeleted();
        this.content = topic.getContent();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount == null ? null : readCount.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}