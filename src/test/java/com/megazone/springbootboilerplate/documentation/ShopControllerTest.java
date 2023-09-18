package com.megazone.springbootboilerplate.documentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import com.megazone.springbootboilerplate.shop.application.ShopReadService;
import com.megazone.springbootboilerplate.shop.application.ShopWriteService;
import com.megazone.springbootboilerplate.shop.application.dto.request.ShopCreateRequest;
import com.megazone.springbootboilerplate.shop.application.dto.response.ShopResponse;
import com.megazone.springbootboilerplate.shop.presentation.controller.ShopController;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
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
        List<ShopResponse> shopResponses = List.of(
            new ShopResponse(1L, "Shop A", "도로명 주소 1", "상세 주소 1", "bronze"),
            new ShopResponse(2L, "Shop B", "도로명 주소 2", "상세 주소 2", "silver"),
            new ShopResponse(3L, "Shop C", "도로명 주소 3", "상세 주소 3", "gold"));
        BDDMockito.given(shopReadService.findAll()).willReturn(shopResponses);

        mockMvc.perform(get("/shops")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("Shop 전체 조회", "Shop 전체 조회",
                commonResponseFields(
                    fieldWithPath("data[].id").description("Shop ID"),
                    fieldWithPath("data[].name").description("Shop 이름"),
                    fieldWithPath("data[].address").description("Shop 도로명 주소"),
                    fieldWithPath("data[].detailAddress").description("Shop 상세 주소"),
                    fieldWithPath("data[].tier").description("Shop 등급")
                )
            ));
    }

    @DisplayName("Shop 조회")
    @Test
    void findShop() throws Exception {
        ShopResponse shopResponse = new ShopResponse(1L, "Shop A", "도로명 주소", "상세 주소", "bronze");
        BDDMockito.given(shopReadService.findById(1L)).willReturn(shopResponse);

        mockMvc.perform(get("/shops/{shopId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("Shop 조회", "Shop 조회",
                    pathParameters(
                        parameterWithName("shopId").description("Shop ID")
                    ),
                    commonResponseFields(
                        fieldWithPath("data.id").description("Shop ID"),
                        fieldWithPath("data.name").description("Shop 이름"),
                        fieldWithPath("data.address").description("Shop 도로명 주소"),
                        fieldWithPath("data.detailAddress").description("Shop 상세 주소"),
                        fieldWithPath("data.tier").description("Shop 등급")
                    )
                )
            );
    }

    @DisplayName("Shop 등록")
    @Test
    void createShop() throws Exception {
        ShopCreateRequest request = new ShopCreateRequest("Shop A", "도로명 주소", "상세 주소");
        ShopResponse response = new ShopResponse(1L, "Shop A", "도로명 주소", "상세 주소", "bronze");
        BDDMockito.given(shopWriteService.create(request)).willReturn(response);

        mockMvc.perform(post("/shops")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andDo(document("Shop 등록", "Shop 등록",
                    requestFields(
                        fieldWithPath("name").description("Shop 이름").attributes(constraintsAttribute("최소 2자 +\n최대 15자")),
                        fieldWithPath("address").description("Shop 도로명 주소"),
                        fieldWithPath("detailAddress").description("Shop 상세 주소")
                    ),
                    responseHeaders(
                        headerWithName("Location").description("리소스 위치")
                    ),
                    commonResponseFields(
                        fieldWithPath("data.id").description("Shop ID"),
                        fieldWithPath("data.name").description("Shop 이름"),
                        fieldWithPath("data.address").description("Shop 도로명 주소"),
                        fieldWithPath("data.detailAddress").description("Shop 상세 주소"),
                        fieldWithPath("data.tier").description("Shop 등급")
                    )
                )
            );
    }

    @DisplayName("Shop 등급 수정")
    @Test
    void updateShop() throws Exception {
        ShopResponse response = new ShopResponse(1L, "Shop A", "도로명 주소", "상세 주소", "silver");
        BDDMockito.given(shopWriteService.update(1L, "upgrade")).willReturn(response);

        mockMvc.perform(put("/shops/{shopId}:{shopAction}", 1L, "upgrade")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("Shop 등급 수정", "Shop 등급 수정",
                    pathParameters(
                        parameterWithName("shopId").description("Shop ID"),
                        parameterWithName("shopAction").description("""
                            upgrade: 업그레이드
                                                                            
                            downgrade: 다운그레이드
                            """
                        )
                    ),
                    commonResponseFields(
                        fieldWithPath("data.id").description("Shop ID"),
                        fieldWithPath("data.name").description("Shop 이름"),
                        fieldWithPath("data.address").description("Shop 도로명 주소"),
                        fieldWithPath("data.detailAddress").description("Shop 상세 주소"),
                        fieldWithPath("data.tier").description("Shop 등급")
                    )
                )
            );
    }
}
