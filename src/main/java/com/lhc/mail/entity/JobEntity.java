package com.lhc.mail.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhc
 * @since 2019-07-20
 */
@TableName("job_entity")
public class JobEntity extends Model<JobEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String group;

    private String cron;

    private String parameter;

    private String description;

    @TableField("vm_param")
    private String vmParam;

    @TableField("jar_path")
    private String jarPath;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getVmParam() {
        return vmParam;
    }

    public void setVmParam(String vmParam) {
        this.vmParam = vmParam;
    }
    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "JobEntity{" +
        "id=" + id +
        ", name=" + name +
        ", group=" + group +
        ", cron=" + cron +
        ", parameter=" + parameter +
        ", description=" + description +
        ", vmParam=" + vmParam +
        ", jarPath=" + jarPath +
        ", status=" + status +
        "}";
    }
}
