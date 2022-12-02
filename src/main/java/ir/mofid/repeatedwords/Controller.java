package ir.mofid.repeatedwords;

import ir.mofid.repeatedwords.requests.RepeatedListRequest;
import ir.mofid.repeatedwords.response.RepeatedListResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public record Controller(RepeatedService service) {

    @PostMapping("/repeated_list")
    public RepeatedListResponse getRepeatedList(@Valid @RequestBody RepeatedListRequest request)
    {
        return new RepeatedListResponse(service.getRepeated(request.numbers()));
    }
}
