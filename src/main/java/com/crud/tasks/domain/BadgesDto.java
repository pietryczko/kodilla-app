package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BadgesDto {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachments")
    private List<AttachmentByTypeDto> attachment;
}
