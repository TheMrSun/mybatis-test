import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.mybatisProxy.MyMapperProxy;
import tk.mybatis.rbac.mapper.UserMapper;
import tk.mybatis.rbac.model.SysUser;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Slience
 * @version 1.0
 */
public class mybatisProxyTest extends BaseMapperTest {

    @Test
    public void test(){
        SqlSession sqlSession = getSqlSession();

        MyMapperProxy userMapperProxy = new MyMapperProxy(UserMapper.class, sqlSession);

        UserMapper userMapper = (UserMapper)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class}, userMapperProxy);

        List<SysUser> sysUsers = userMapper.selectAll();

        System.out.println(sysUsers.toString());
    }
}
