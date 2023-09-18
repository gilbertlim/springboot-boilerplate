package com.megazone.springbootboilerplate.documentation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.*;
import org.springframework.restdocs.snippet.Attributes.Attribute;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(RestDocumentationExtension.class)
abstract public class Documentation {

    private static final List<FieldDescriptor> COMMON_FIELDS = List.of(
        fieldWithPath("code").description("API 응답 코드"),
        fieldWithPath("message").description("API 응답 메시지"));

    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(documentationConfiguration(restDocumentation)
                .snippets().withDefaults(httpRequest(), httpResponse())
                .and()
                .operationPreprocessors()
                .withRequestDefaults(modifyHeaders().remove("Host"), prettyPrint())
                .withResponseDefaults(modifyHeaders().remove("Date"), prettyPrint())
                .withResponseDefaults(modifyHeaders().remove("Vary"), prettyPrint())
            )
            .alwaysDo(print())
            .addFilters()
            .build();
    }

    protected RestDocumentationResultHandler document(Snippet... snippets) {
        return document("", snippets);
    }

    protected RestDocumentationResultHandler document(String description, Snippet... snippets) {
        return document(description, "", snippets);
    }

    protected RestDocumentationResultHandler document(String description, String summary, Snippet... snippets) {
        return MockMvcRestDocumentationWrapper.document("{class-name}/{method-name}", description, summary, snippets);
    }

    protected static ResponseFieldsSnippet commonResponseFields(FieldDescriptor... descriptors) {
        List<FieldDescriptor> fields = Stream.concat(COMMON_FIELDS.stream(), Arrays.stream(descriptors)).toList();
        return PayloadDocumentation.responseFields(fields);
    }

    protected static Attribute constraintsAttribute(String text) {
        return key("constraints").value(text);
    }
}
