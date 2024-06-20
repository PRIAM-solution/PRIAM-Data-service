package priam.data.priamdataservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import priam.data.priamdataservice.security.FeignClientConfiguration;

@FeignClient(name = "RIGHT-SERVICE",
        url = "http://localhost:8083",
        configuration = FeignClientConfiguration.class)
public interface RightRestClient {
    @GetMapping(path = "api/isAccepted")
    public boolean getIfDataAccessAccepted(@RequestParam int dataSubjectId, @RequestParam int dataId);
}

