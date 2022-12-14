package tk.mybatis.rbac.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Slience
 * @version 1.0
 */
public class EnabledTypeHandler implements TypeHandler<Enabled> {


    private final Map<Integer, Enabled> enabledMap =  new HashMap<Integer, Enabled>();

    public EnabledTypeHandler(){
        for(Enabled enabled : Enabled.values()){
            enabledMap.put(enabled.getValue(),enabled);
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Enabled enabled, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,enabled.getValue());
    }

    @Override
    public Enabled getResult(ResultSet resultSet, String s) throws SQLException {
        int value = resultSet.getInt(s);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(ResultSet resultSet, int i) throws SQLException {
        int value = resultSet.getInt(i);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(CallableStatement callableStatement, int i) throws SQLException {
        int value = callableStatement.getInt(i);
        return enabledMap.get(value);
    }
}
