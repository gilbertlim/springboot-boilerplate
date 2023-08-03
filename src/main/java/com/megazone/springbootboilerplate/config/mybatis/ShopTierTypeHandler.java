package com.megazone.springbootboilerplate.config.mybatis;

import com.megazone.springbootboilerplate.shop.domain.tier.Bronze;
import com.megazone.springbootboilerplate.shop.domain.tier.Gold;
import com.megazone.springbootboilerplate.shop.domain.tier.ShopTier;
import com.megazone.springbootboilerplate.shop.domain.tier.Silver;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopTierTypeHandler extends BaseTypeHandler<ShopTier> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ShopTier parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public ShopTier getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return switch (code) {
            case "gold" -> new Gold();
            case "silver" -> new Silver();
            case "bronze" -> new Bronze();
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
