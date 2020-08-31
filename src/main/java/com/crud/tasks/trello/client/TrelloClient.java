package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PropertySource("classpath:application.properties")
@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod:trelloApiEndpoint}")
    private String trelloApiEndpoint;

    @Value("${trello.api.key:trelloAppKey}")
    private String trelloAppKey;

    @Value("${trello.api.token:trelloToken}")
    private String trelloToken;

    @Value("${trello.api.username:trelloUsername")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    public String buildUrl() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername +
                "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
        return url.toString();
    }

    public List<TrelloBoardDto> getTrelloBoards() {
        buildUrl();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildUrl(), TrelloBoardDto[].class);

        if(boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }
}
