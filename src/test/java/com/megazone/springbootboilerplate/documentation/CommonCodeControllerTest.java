package com.megazone.springbootboilerplate.documentation;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.megazone.springbootboilerplate.common.dto.ResponseType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

public class CommonCodeControllerTest extends Documentation {

    @Test
    void codes() throws Exception {
        mockMvc.perform(get("/codes")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(document("code-response",
                CodeResponseFieldsSnippet.of("code-response", fieldDescriptors())));
    }

    private List<FieldDescriptor> fieldDescriptors() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();

        for (ResponseType code : ResponseType.values()) {
            FieldDescriptor attributes =
                fieldWithPath(code.name()).type(JsonFieldType.OBJECT)
                    .attributes(
                        key("code").value(code.getCode()),
                        key("message").value(code.getMessage()),
                        key("status").value(code.getStatus()));
            fieldDescriptors.add(attributes);
        }

        return fieldDescriptors;
    }
}
