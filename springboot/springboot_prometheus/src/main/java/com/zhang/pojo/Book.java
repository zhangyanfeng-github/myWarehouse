package com.zhang.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName book
 */
//@TableName(value ="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    @TableId
    private Integer id;

    private String name;

    private String author;

    private String isbn;

    private Date publishDate;

    private BigDecimal price;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}