package com.bestbuy.api.automation.models.Request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Getter
@Setter
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetProductsRequest {

    private int $limit;
    private int $skip;
}
