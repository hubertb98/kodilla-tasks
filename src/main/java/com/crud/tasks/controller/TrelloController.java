package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        List<TrelloBoardDto> kodillaBoards = trelloBoards.stream()
                .filter(tb -> tb.getName() != null && tb.getId() != null)
                .filter(tb -> tb.getName().contains("Kodilla"))
                .collect(Collectors.toList());

        trelloBoards.forEach(tb -> {

            System.out.println("\n" + tb.getName() + " - " + tb.getId());

            System.out.println("This board contains lists: ");

            tb.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " (" + trelloList.getId() + ") - " + trelloList.isClosed()));

        });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}