package tk.mybatis.rbac.mapper;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import tk.mybatis.rbac.model.SysRole;

import java.util.List;

/**
 * @author Slience
 * @version 1.0
 */

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {

    List<SysRole> selectRolePrivilege();


    List<SysRole> selectRoleChoose(Integer roleId);


    SysRole selectRoleById(Integer id);

    int updateById(SysRole role);

}
