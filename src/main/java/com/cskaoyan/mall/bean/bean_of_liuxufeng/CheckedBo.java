package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import java.util.List;

public class CheckedBo {

    /**
     * productIds : [33]
     * isChecked : 0
     */

    private Boolean isChecked;
    private List<Integer> productIds;

    public Boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
}
