import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

/**
 * @author Slience
 * @version 1.0
 */
public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Country> countryList = sqlSession.selectList("selectAll");
            System.out.println(countryList.toString());
        } finally {
            sqlSession.close();
        }

    }


}
