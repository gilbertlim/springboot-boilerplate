package com.boilerplate.shop.infrastructure.provider.mybatis.typehandler;

import java.sql.*;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.boilerplate.shop.domain.data.vo.type.*;

public class ShopTierTypeHandler extends BaseTypeHandler<ShopTier> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ShopTier parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ShopTier getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return switch (code) {
            case "Gold" -> new Gold();
            case "Silver" -> new Silver();
            case "Bronze" -> new Bronze();
            default -> new Bronze();
        };
    }

    @Override
    public ShopTier getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public ShopTier getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
