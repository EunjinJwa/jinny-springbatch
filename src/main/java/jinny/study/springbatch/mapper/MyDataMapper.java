package jinny.study.springbatch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import jinny.study.springbatch.domain.entity.MyData;
import org.springframework.jdbc.core.RowMapper;

public class MyDataMapper implements RowMapper<MyData> {

    @Override
    public MyData mapRow(ResultSet rs, int rowNum) throws SQLException {
        MyData myData = new MyData();
        myData.setId(rs.getLong("id"));
        myData.setName(rs.getString("name"));
        myData.setValue(rs.getInt("value"));
        return myData;
    }
}
