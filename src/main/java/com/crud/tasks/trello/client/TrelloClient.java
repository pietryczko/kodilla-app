package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.user}")
    private String trelloUser;

    public List<TrelloBoardDto> getTrelloBoards() {
        TrelloBoardDto[] boardsResponse = buildURL(trelloApiEndpoint, trelloUser, trelloAppKey, trelloToken);
        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    private TrelloBoardDto[] buildURL(String apiEndpoint, String userName, String key, String token) {
        URI url = UriComponentsBuilder.fromHttpUrl(apiEndpoint + "/members/" + userName + "/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("fields", "name,id")
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(url, TrelloBoardDto[].class);
    }
}