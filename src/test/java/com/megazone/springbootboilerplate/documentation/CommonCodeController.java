package com.megazone.springbootboilerplate.documentation;

import com.megazone.springbootboilerplate.common.dto.ResponseType;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonCodeController {

    @GetMapping(value = "/codes")
    public Map<String, CodeResponse> getCodes() {
        return Arrays.stream(ResponseType.values())
            .collect(Collectors.toMap(Enum::name, CodeResponse::new));
    }

    protected static class CodeResponse {
        private String code;
        private String message;
        private int status;

        public CodeResponse() {
        }

        public CodeResponse(ResponseType responseType) {
            this.code = responseType.getCode();
            this.message = responseType.getMessage();
            this.status = responseType.getStatus().value();
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }
}
