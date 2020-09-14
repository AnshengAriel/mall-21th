package com.cskaoyan.mall.bean.statistic;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderBean {

    String amount;
    String orders;
    String customers;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    Date day;
    String pcr;
}
