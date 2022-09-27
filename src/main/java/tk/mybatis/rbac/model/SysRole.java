package tk.mybatis.rbac.model;

import lombok.Data;
import tk.mybatis.rbac.type.Enabled;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Slience
 * @version 1.0
 */

@Data
public class SysRole implements Serializable {

    private Integer id;
    private String roleName;
    private Enabled enabled;
    private Integer createBy;
    private Date createTime;

    private SysUser user;


    public Enabled getEnabled() {
        return enabled;
    }

    public void setEnabled(Enabled enabled) {
        this.enabled = enabled;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    private List<SysPrivilege> privilegeList;

    public List<SysPrivilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<SysPrivilege> privilegeList) {
        this.privilegeList = privilegeList;
    }
}
