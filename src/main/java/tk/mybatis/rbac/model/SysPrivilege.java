package tk.mybatis.rbac.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Slience
 * @version 1.0
 */

@Data
public class SysPrivilege implements Serializable {

    private Integer id;

    private String privilegeName;

    private String privilegeUrl;
}
