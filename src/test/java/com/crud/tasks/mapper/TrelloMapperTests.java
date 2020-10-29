package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TrelloMapperTests {
    @Autowired
    private TrelloMapper trelloMapper;


    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloListDto> listDto = new ArrayList<>();
        List<TrelloBoardDto> boardsDto = new ArrayList<>();

        TrelloBoardDto board = new TrelloBoardDto("1", "Test", listDto);
        boardsDto.add(board);

        //When
        List<TrelloBoard> expectedBoard = trelloMapper.mapToBoards(boardsDto);
        String idDto = boardsDto.get(0).getId();
        String nameDto = boardsDto.get(0).getName();
        List listsDto = boardsDto.get(0).getLists();

        String expectedId = expectedBoard.get(0).getId();
        String expectedName = expectedBoard.get(0).getName();
        List expectedLists = expectedBoard.get(0).getLists();


        //Then
        assertThat(idDto).isEqualTo(expectedId);
        assertThat(nameDto).isEqualTo(expectedName);
        assertThat(listsDto).isEqualTo(expectedLists);
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        TrelloBoard board = new TrelloBoard("1", "Test", trelloLists);
        trelloBoards.add(board);

        //When
        List<TrelloBoardDto> expectedBoardDto = trelloMapper.mapToBoardsDto(trelloBoards);
        String id = trelloBoards.get(0).getId();
        String name = trelloBoards.get(0).getName();
        List lists = trelloBoards.get(0).getLists();

        String expectedId = expectedBoardDto.get(0).getId();
        String expectedName = expectedBoardDto.get(0).getName();
        List expectedLists = expectedBoardDto.get(0).getLists();

        //Then
        assertThat(id).isEqualTo(expectedId);
        assertThat(name).isEqualTo(expectedName);
        assertThat(lists).isEqualTo(expectedLists);
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto listDto = new TrelloListDto("1", "Test List", false);

        trelloListsDto.add(listDto);

        //When
        List<TrelloList> expectedTrelloList = trelloMapper.mapToList(trelloListsDto);

        String id = trelloListsDto.get(0).getId();
        String name = trelloListsDto.get(0).getName();
        boolean isClosed = trelloListsDto.get(0).isClosed();

        String expectedId= expectedTrelloList.get(0).getId();
        String expectedName= expectedTrelloList.get(0).getName();
        boolean expectedIsClosed= expectedTrelloList.get(0).isClosed();

        //Then
        assertThat(id).isEqualTo(expectedId);
        assertThat(name).isEqualTo(expectedName);
        assertThat(isClosed).isEqualTo(expectedIsClosed);
    }

    @Test
    void mapToListsDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList list = new TrelloList("1", "Test List", false);

        trelloLists.add(list);

        //When
        List<TrelloListDto> expectedTrelloListDto = trelloMapper.mapToListsDto(trelloLists);

        String id = trelloLists.get(0).getId();
        String name = trelloLists.get(0).getName();
        boolean isClosed = trelloLists.get(0).isClosed();

        String expectedId= expectedTrelloListDto.get(0).getId();
        String expectedName= expectedTrelloListDto.get(0).getName();
        boolean expectedIsClosed= expectedTrelloListDto.get(0).isClosed();

        //Then
        assertThat(id).isEqualTo(expectedId);
        assertThat(name).isEqualTo(expectedName);
        assertThat(isClosed).isEqualTo(expectedIsClosed);
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test name", "Test desc",
                "Test pos", "1");

        //When
        TrelloCardDto expectedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        String name = trelloCard.getName();
        String desc = trelloCard.getDescription();
        String pos = trelloCard.getPos();
        String listId = trelloCard.getListId();

        String expectedName = expectedTrelloCardDto.getName();
        String expectedDesc = expectedTrelloCardDto.getDescription();
        String expectedPos = expectedTrelloCardDto.getPos();
        String expectedListId = expectedTrelloCardDto.getListId();

        //Then
        assertThat(name).isEqualTo(expectedName);
        assertThat(desc).isEqualTo(expectedDesc);
        assertThat(pos).isEqualTo(expectedPos);
        assertThat(listId).isEqualTo(expectedListId);
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test name", "Test desc",
                "Test pos", "1");

        //When
        TrelloCard expectedTrelloCard = trelloMapper.mapToCard(trelloCardDto);

        String name = trelloCardDto.getName();
        String desc = trelloCardDto.getDescription();
        String pos = trelloCardDto.getPos();
        String listId = trelloCardDto.getListId();

        String expectedName = expectedTrelloCard.getName();
        String expectedDesc = expectedTrelloCard.getDescription();
        String expectedPos = expectedTrelloCard.getPos();
        String expectedListId = expectedTrelloCard.getListId();

        //Then
        assertThat(name).isEqualTo(expectedName);
        assertThat(desc).isEqualTo(expectedDesc);
        assertThat(pos).isEqualTo(expectedPos);
        assertThat(listId).isEqualTo(expectedListId);
    }
}