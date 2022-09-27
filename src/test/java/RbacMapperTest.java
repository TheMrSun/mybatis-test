import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.rbac.mapper.PrivilegeMapper;
import tk.mybatis.rbac.mapper.RoleMapper;
import tk.mybatis.rbac.mapper.UserMapper;
import tk.mybatis.rbac.mapper.UserRoleMapper;
import tk.mybatis.rbac.model.SysPrivilege;
import tk.mybatis.rbac.model.SysRole;
import tk.mybatis.rbac.model.SysUser;
import tk.mybatis.rbac.type.Enabled;

import java.util.Date;
import java.util.List;


/**
 * @author Slience
 * @version 1.0
 */
public class RbacMapperTest extends BaseMapperTest {

    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            List<SysUser> users = sqlSession.selectList("tk.mybatis.rbac.mapper.UserMapper.selectAll");
            System.out.println(users.toString());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectById(1);
            Assert.assertNotNull(sysUser);
            Assert.assertEquals("admin", sysUser.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserRoleMapper mapper = sqlSession.getMapper(UserRoleMapper.class);
            List<SysRole> sysRoles = mapper.selectRoleByUserId(1);
            System.out.println(sysRoles.toString());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();

            user.setUserName("test001");
            user.setUserPassword("123456");
            user.setUserEmail("test001@mybatis.com");
            user.setUserInfo("test001.info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());

            int result = userMapper.insertUser(user);

            Assert.assertEquals(1, result);

            Assert.assertNotNull(user.getId());

            System.out.println(user.getId());

        } finally {

            //不影响数据可以进行回滚操作
            sqlSession.rollback();
            //sqlSession.commit();
            sqlSession.close();
        }
    }


    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();

            user.setUserName("test001");
            user.setUserPassword("123456");
            user.setUserEmail("test001@mybatis.com");
            user.setUserInfo("test001.info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());

            int result = userMapper.insertUser2(user);

            Assert.assertEquals(1, result);

            Assert.assertNotNull(user.getId());

            System.out.println(user.getId());

        } finally {

            //不影响数据可以进行回滚操作
            //sqlSession.rollback();
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectById(1);

            Assert.assertEquals("admin", user.getUserName());

            user.setUserName("admin_test");

            int result = userMapper.updateUserById(user);

            Assert.assertEquals(1, result);

            user = userMapper.selectById(1);

            Assert.assertEquals("admin_test", user.getUserName());


        } finally {
            sqlSession.rollback();
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();

        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(2);

            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2() {
        SqlSession sqlSession = getSqlSession();

        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleByIdSelect(2);

            Assert.assertNotNull(user);
            System.out.println(user.equals(null));
            System.out.println("调用user.getRole");
            Assert.assertNotNull(user.getRole());

        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAllUserAndRole() {
        SqlSession sqlSession = getSqlSession();

        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> users = userMapper.selectAllUserAndRole();
            System.out.println(users.size());
            System.out.println(users.toString());

        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAllUserAndRoles() {
        SqlSession sqlSession = getSqlSession();

        try {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> users = userMapper.selectAllUserAndRoles();
            System.out.println(users.size());
            System.out.println(users.toString());

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolePrivilege() {
        SqlSession sqlSession = getSqlSession();

        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = roleMapper.selectRolePrivilege();
            //System.out.println(roles.toString());
            System.out.println(roles.get(0).getPrivilegeList());

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectRoleChoose() {
        SqlSession sqlSession = getSqlSession();
        try {

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = roleMapper.selectRoleChoose(2);
            System.out.println(roles);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateRoleById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectRoleById(2);
            System.out.println(role);
            Assert.assertEquals(Enabled.enabled, role.getEnabled());
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);

        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testL1Cache() {
        SqlSession sqlSession = getSqlSession();
        SysUser user1 = null;

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            user1 = userMapper.selectById(2);
            user1.setUserName("new name");

            System.out.println(user1.toString());


            SysUser user2 = userMapper.selectById(2);

            System.out.println(user2.toString());
            System.out.println(user1.hashCode());
            System.out.println(user2.hashCode());

            Assert.assertEquals("new name", user2.getUserName());

            Assert.assertEquals(user1, user2);


        } finally {
            sqlSession.close();
        }


        sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user2 = userMapper.selectById(2);
            System.out.println(user1.toString());
            System.out.println(user2.toString());
            System.out.println(user1.hashCode());
            System.out.println(user2.hashCode());

            Assert.assertEquals("new name", user2.getUserName());

            Assert.assertEquals(user1, user2);


        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache() {
        SqlSession sqlSession = getSqlSession();
        SysRole role1 = null;
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            role1 = roleMapper.selectRoleById(1);

            role1.setRoleName("new roleName");

            SysRole role2 = roleMapper.selectRoleById(1);

            Assert.assertEquals("new roleName", role2.getRoleName());

            Assert.assertEquals(role1, role2);


        } finally {
            sqlSession.close();
        }

        //将数据保存至二级缓存

        System.out.println("开启另外一个sqlsession");
        sqlSession = getSqlSession();

        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role3 = roleMapper.selectRoleById(1);

            Assert.assertEquals("new roleName", role3.getRoleName());
            System.out.println(role1.hashCode());
            System.out.println(role3.hashCode());
            Assert.assertEquals(role1, role3);

            SysRole role4 = roleMapper.selectRoleById(1);
            System.out.println(role3.hashCode());
            System.out.println(role4.hashCode());
            Assert.assertEquals(role3, role4);

            role3.setRoleName("new role3Name");
            role4.setRoleName("new role4Name");

            System.out.println(role3.toString());
            System.out.println(role4.toString());

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testEhcache() {
        SqlSession sqlSession = getSqlSession();
        SysUser user1 = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            user1 = userMapper.selectById(1);

            user1.setUserName("new user1Name");

            SysUser user2 = userMapper.selectById(1);

            Assert.assertEquals("new user1Name", user2.getUserName());

            Assert.assertEquals(user1, user2);


        } finally {
            sqlSession.close();
        }

        //将数据保存至二级缓存

        System.out.println("开启另外一个sqlsession");
        sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user3 = userMapper.selectById(1);

            Assert.assertEquals("new user1Name", user3.getUserName());
            System.out.println(user1.hashCode());
            System.out.println(user3.hashCode());
            Assert.assertEquals(user1, user3);

            SysUser user4 = userMapper.selectById(1);
            System.out.println(user3.hashCode());
            System.out.println(user4.hashCode());
            Assert.assertEquals(user3, user4);

            user3.setUserName("new user3Name");
            user4.setUserName("new user4Name");

            System.out.println(user3.toString());
            System.out.println(user4.toString());

        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testRedisCache() {
        SqlSession sqlSession = getSqlSession();
        SysPrivilege privilege1 = null;
        try {
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);

            privilege1 = privilegeMapper.selectPrivilegeById(1);

            privilege1.setPrivilegeName("new privilege1Name");

            SysPrivilege privilege2 = privilegeMapper.selectPrivilegeById(1);

            Assert.assertEquals("new privilege1Name", privilege2.getPrivilegeName());

            Assert.assertEquals(privilege1, privilege2);


        } finally {
            sqlSession.close();
        }

        //将数据保存至二级缓存

        System.out.println("开启另外一个sqlsession");
        sqlSession = getSqlSession();

        try {
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);

            SysPrivilege privilege3 = privilegeMapper.selectPrivilegeById(1);

            Assert.assertEquals("new privilege1Name", privilege3.getPrivilegeName());
            System.out.println(privilege1.hashCode());
            System.out.println(privilege3.hashCode());
            Assert.assertEquals(privilege1, privilege3);

            SysPrivilege privilege4 = privilegeMapper.selectPrivilegeById(1);
            System.out.println(privilege3.hashCode());
            System.out.println(privilege4.hashCode());
            Assert.assertEquals(privilege3, privilege4);

            privilege3.setPrivilegeName("new privilege3Name");
            privilege4.setPrivilegeName("new privilege4Name");

            System.out.println(privilege3.toString());
            System.out.println(privilege4.toString());

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 脏读 测试 二级缓存在update 时做了刷新，未出现脏读
     */
    @Test
    public void testCache() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = userMapper.selectUserAndRoleById(2);

            Assert.assertEquals("普通用户", user.getRole().getRoleName());

            System.out.println("角色名称" + user.getRole().getRoleName());

        } finally {
            sqlSession.close();
        }

        //开启另外一个session
        sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectRoleById(2);
            role.setRoleName("脏数据");
            roleMapper.updateById(role);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        System.out.println("role 更改");

        sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysUser user = userMapper.selectUserAndRoleById(1);
            SysRole role = roleMapper.selectRoleById(2);

            Assert.assertEquals("普通用户",user.getRole().getRoleName());
            Assert.assertEquals("脏数据",role.getRoleName());
            System.out.println("角色名" + user.getRole().getRoleName());

            role.setRoleName("普通用户");
            roleMapper.updateById(role);

            sqlSession.commit();



        } finally {
            sqlSession.close();
        }


    }


}
