package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoard() {
        //GIVEN
        List<TrelloListDto> testList = new ArrayList<>();
        List<TrelloListDto> testList2 = new ArrayList<>();
        TrelloBoardDto testTrelloBoardDto = new TrelloBoardDto("1", "testName", testList);
        TrelloBoardDto testTrelloBoardDto2 = new TrelloBoardDto("2", "testName2", testList2);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(testTrelloBoardDto);
        trelloBoardDtos.add(testTrelloBoardDto2);

        //WHEN
        List<TrelloBoard> mappedTrelloBoard = trelloMapper.mapToBoards(trelloBoardDtos);


        //THEN
        assertEquals(2, mappedTrelloBoard.size());

    }

    @Test
    public void testMapToBoardDto() {
        //GIVEN
        List<TrelloList> testList = new ArrayList<>();
        List<TrelloList> testList2 = new ArrayList<>();
        TrelloBoard testTrelloBoard = new TrelloBoard("1", "testName", testList);
        TrelloBoard testTrelloBoard2 = new TrelloBoard("2", "testName2", testList2);
        List<TrelloBoard> testTrelloBoards = new ArrayList<>();
        testTrelloBoards.add(testTrelloBoard);
        testTrelloBoards.add(testTrelloBoard2);

        //WHEN
        List<TrelloBoardDto> mappedTrelloBoard = trelloMapper.mapToBoardsDto(testTrelloBoards);

        //THEN
        assertEquals(2, mappedTrelloBoard.size());

    }

    @Test
    public void testMapToList() {
        //GIVEN
        TrelloListDto testTrelloListDto = new TrelloListDto("1", "testName", true);
        TrelloListDto testTrelloListDto2 = new TrelloListDto("2", "testName2", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(testTrelloListDto);
        trelloListDtos.add(testTrelloListDto2);

        //WHEN
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloListDtos);
        //THEN
        assertEquals(2, mappedList.size());

    }

    @Test
    public void testMapToLisDto() {
        //GIVEN
        TrelloList testList = new TrelloList("1", "testName", true);
        TrelloList testList2 = new TrelloList("2", "testName2", false);
        List<TrelloList> testTrelloLists = new ArrayList<>();
        testTrelloLists.add(testList);
        testTrelloLists.add(testList2);

        //WHEN
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(testTrelloLists);
        //THEN
        assertEquals(2, mappedList.size());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test name", "test description", "test pos", "test id");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "test id");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());

    }
}