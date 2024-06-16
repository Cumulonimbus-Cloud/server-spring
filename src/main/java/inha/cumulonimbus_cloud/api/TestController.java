package inha.cumulonimbus_cloud.api;

import inha.cumulonimbus_cloud.common.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "test controller", description = "test 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping
    public BaseResponse<String> test() {
        return BaseResponse.onSuccess("test success");
    }
}
