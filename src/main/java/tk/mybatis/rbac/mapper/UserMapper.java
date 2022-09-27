package tk.mybatis.rbac.mapper;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import tk.mybatis.rbac.model.SysUser;

import java.util.List;

/**
 * @author Slience
 * @version 1.0
 */

@CacheNamespaceRef(UserMapper.class)
public interface UserMapper {

    /**
     * 根据id 查询用户信息
     * @param id
     * @return
     */
    SysUser selectById(Integer id);


    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 增加用户
     * @param user
     * @return
     */
    int insertUser(SysUser user);


    int insertUser2(SysUser user);

    int updateUserById(SysUser user);


    SysUser selectUserAndRoleById(Integer userId);


    SysUser selectUserAndRoleByIdSelect(Integer userId);

    List<SysUser> selectAllUserAndRole();

    List<SysUser> selectAllUserAndRoles();


}
