package priam.data.priamdataservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import priam.data.priamdataservice.dto.IsAcceptedDTO;

@FeignClient(name = "RIGHT-SERVICE")
public interface RightRestClient {
    @PostMapping(path = "api/isAccepted")
    public boolean getIfDataAccessAccepted(@RequestBody IsAcceptedDTO isAcceptedDTO);
}

