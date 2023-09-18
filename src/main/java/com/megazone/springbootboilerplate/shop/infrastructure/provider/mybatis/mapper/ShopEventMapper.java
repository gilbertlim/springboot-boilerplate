package com.megazone.springbootboilerplate.shop.infrastructure.provider.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.megazone.springbootboilerplate.shop.domain.event.ShopTierEvent;

@Mapper
public interface ShopEventMapper {

    @ResultMap("shopTierEventRowMapper")
    @Select("""
        select event_id, event_type, domain_id
        from event_outbox_shop
        where published = 'N'
    """)
    List<ShopTierEvent> findByNotPublished();

    @Options(useGeneratedKeys = true, keyProperty = "eventId")
    @Insert("""
        insert into event_outbox_shop (domain_id, event_type)
        values (#{domainId}, #{eventType})
        """)
    void save(ShopTierEvent shopTierEvent);

    @Update("""
        update event_outbox_shop set published = 'Y'
        where event_id = #{eventId}
        """)
    void updateToPublished(ShopTierEvent shopTierEvent);
}
