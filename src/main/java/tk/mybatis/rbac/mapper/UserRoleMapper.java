package tk.mybatis.rbac.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.rbac.model.SysRole;

import java.util.List;

/**
 * @author Slience
 * @version 1.0
 */
public interface UserRoleMapper {

    /**
     * 根据userId 查询角色
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(@Param("userId") Integer userId);
}
