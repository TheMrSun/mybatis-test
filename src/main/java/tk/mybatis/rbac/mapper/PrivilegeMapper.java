package tk.mybatis.rbac.mapper;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import tk.mybatis.rbac.model.SysPrivilege;

import java.util.List;

/**
 * @author Slience
 * @version 1.0
 */
@CacheNamespaceRef(PrivilegeMapper.class)
public interface PrivilegeMapper {

    SysPrivilege selectPrivilegeById(Integer id);
}
