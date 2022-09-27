package tk.mybatis.rbac.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Slience
 * @version 1.0
 */
@Data
public class SysUser implements Serializable {

    private Integer id;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userInfo;

    private byte[] headImg;

    private Date createTime;

    private SysRole role;

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }


    private List<SysRole> roleList;

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
