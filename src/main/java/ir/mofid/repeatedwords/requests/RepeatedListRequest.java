package ir.mofid.repeatedwords.requests;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RepeatedListRequest( @NotNull(message = "numbers is null!") List<Integer> numbers) {
}
