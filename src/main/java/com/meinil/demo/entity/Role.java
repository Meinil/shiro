package com.meinil.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Data
@TableName("t_role")
public class Role implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    @TableField(exist = false)
    private List<Permission> permissions;
}
