package com.boilerplate.documentation;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.boilerplate.common.dto.CommonResponseType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;

@WebMvcTest(CommonCodeController.class)
class CommonCodeControllerTest extends Documentation {

    @DisplayName("공통 코드 문서화")
    @Test
    void codes() throws Exception {
        mockMvc.perform(get("/codes")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcRestDocumentation.document("code-response",
                CodeResponseFieldsSnippet.of("code-response", fieldDescriptors())));
    }

    private List<FieldDescriptor> fieldDescriptors() {
        return Arrays.stream(CommonResponseType.values())
            .map(code -> fieldWithPath(code.name())
                .type(JsonFieldType.OBJECT)
                .attributes(
                    key("code").value(code.getCode()),
                    key("message").value(code.getMessage()),
                    key("status").value(code.getStatus())
                )
            ).toList();
    }
}
