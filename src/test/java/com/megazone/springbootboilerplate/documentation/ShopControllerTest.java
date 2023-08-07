package com.megazone.springbootboilerplate.documentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.springbootboilerplate.shop.service.ShopReadService;
import com.megazone.springbootboilerplate.shop.service.ShopWriteService;
import com.megazone.springbootboilerplate.shop.service.dto.request.ShopCreateRequest;
import com.megazone.springbootboilerplate.shop.service.dto.response.ShopResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ShopControllerTest extends Documentation {

    @MockBean
    private ShopReadService shopReadService;
    @MockBean
    private ShopWriteService shopWriteService;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Shop 리스트 조회")
    @Test
    void findShops() throws Exception {
        List<ShopResponse> shopResponses = List.of(new ShopResponse(1L, "Shop A", "bronze"), new ShopResponse(2L, "Shop B", "silver"), new ShopResponse(3L, "Shop C", "gold"));
        BDDMockito.given(shopReadService.findAll()).willReturn(shopResponses);

        mockMvc.perform(get("/shops")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-shops",
                                responseFields(
                                        fieldWithPath("[].id").description("Shop ID"),
                                        fieldWithPath("[].name").description("Shop 이름"),
                                        fieldWithPath("[].tier").description("Shop 등급")
                                )
                        )
                );
    }

    @DisplayName("Shop 조회")
    @Test
    void findShop() throws Exception {
        ShopResponse shopResponse = new ShopResponse(1L, "Shop A", "bronze");
        BDDMockito.given(shopReadService.findById(1L)).willReturn(shopResponse);

        mockMvc.perform(get("/shops/{shopId}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-shop",
                                pathParameters(
                                        parameterWithName("shopId").description("Shop ID")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("Shop ID"),
                                        fieldWithPath("name").description("Shop 이름"),
                                        fieldWithPath("tier").description("Shop 등급")
                                )
                        )
                );
    }

    @DisplayName("Shop 등록")
    @Test
    void createShop() throws Exception {
        ShopCreateRequest request = new ShopCreateRequest("Shop A");
        ShopResponse response = new ShopResponse(1L, "Shop A", "bronze");
        BDDMockito.given(shopWriteService.create(request)).willReturn(response);

        mockMvc.perform(post("/shops")
                        .content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("post-shops",
                                requestFields(
                                        fieldWithPath("name").description("Shop 이름")
                                ),
                                responseHeaders(
                                        headerWithName("Location").description("리소스 위치")
                                )
                        )
                );
    }

    @DisplayName("Shop 등급 수정")
    @Test
    void updateShop() throws Exception {
        ShopResponse response = new ShopResponse(1L, "Shop A", "silver");
        BDDMockito.given(shopWriteService.update(1L, "upgrade")).willReturn(response);

        mockMvc.perform(put("/shops/{shopId}:{shopAction}", 1L, "upgrade")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("put-shop",
                                pathParameters(
                                        parameterWithName("shopId").description("Shop ID"),
                                        parameterWithName("shopAction").description("""
                                                upgrade: 업그레이드
                                                
                                                downgrade: 다운그레이드
                                                """
                                        )
                                ),
                                responseFields(
                                        fieldWithPath("id").description("Shop ID"),
                                        fieldWithPath("name").description("Shop 이름"),
                                        fieldWithPath("tier").description("Shop 등급")
                                )
                        )
                );
    }
}