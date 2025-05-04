package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloBoard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void testMapToBoards() {
        // given
        TrelloListDto listDto = new TrelloListDto("1", "List 1", false);
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "Board 1", List.of(listDto));
        List<TrelloBoardDto> boardDtos = List.of(boardDto);

        // when
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardDtos);

        // then
        assertEquals(1, boards.size());
        assertEquals("Board 1", boards.get(0).getName());
        assertEquals("1", boards.get(0).getLists().get(0).getId());
    }

    @Test
    void testMapToBoardsDto() {
        // given
        TrelloList list = new TrelloList("1", "List 1", false);
        TrelloBoard board = new TrelloBoard("1", "Board 1", List.of(list));
        List<TrelloBoard> boards = List.of(board);

        // when
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(boards);

        // then
        assertEquals(1, boardDtos.size());
        assertEquals("Board 1", boardDtos.get(0).getName());
        assertEquals("1", boardDtos.get(0).getLists().get(0).getId());
    }

    @Test
    void testMapToList() {
        // given
        TrelloListDto listDto = new TrelloListDto("1", "List name", true);
        List<TrelloListDto> listDtos = List.of(listDto);

        // when
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);

        // then
        assertEquals(1, lists.size());
        assertEquals("List name", lists.get(0).getName());
        assertTrue(lists.get(0).isClosed());
    }

    @Test
    void testMapToListDto() {
        // given
        TrelloList list = new TrelloList("1", "List name", false);
        List<TrelloList> lists = List.of(list);

        // when
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(lists);

        // then
        assertEquals(1, listDtos.size());
        assertEquals("1", listDtos.get(0).getId());
        assertFalse(listDtos.get(0).isClosed());
    }

    @Test
    void testMapToCard() {
        // given
        TrelloCardDto dto = new TrelloCardDto("Card name", "desc", "top", "123");

        // when
        TrelloCard card = trelloMapper.mapToCard(dto);

        // then
        assertEquals("Card name", card.getName());
        assertEquals("desc", card.getDescription());
        assertEquals("top", card.getPos());
        assertEquals("123", card.getListId());
    }

    @Test
    void testMapToCardDto() {
        // given
        TrelloCard card = new TrelloCard("Name", "Desc", "bottom", "321");

        // when
        TrelloCardDto dto = trelloMapper.mapToCardDto(card);

        // then
        assertEquals("Name", dto.getName());
        assertEquals("Desc", dto.getDescription());
        assertEquals("bottom", dto.getPos());
        assertEquals("321", dto.getListId());
    }
}
