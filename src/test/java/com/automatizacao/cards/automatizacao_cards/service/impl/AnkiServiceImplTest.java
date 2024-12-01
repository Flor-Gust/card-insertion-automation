package com.automatizacao.cards.automatizacao_cards.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.automatizacao.cards.automatizacao_cards.client.AnkiClient;
import com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.common_exception.CommonExceptionBadRequest;
import com.automatizacao.cards.automatizacao_cards.hanlde_exceptions.exceptions.common_exception.CommonExceptionNotFound;
import com.automatizacao.cards.automatizacao_cards.model.Notes;

class AnkiServiceImplTest {

  @Mock
  private AnkiClient ankiClient;

  @InjectMocks
  private AnkiServiceImpl ankiService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSendNotesToAnki_Success() throws IOException {
    MultipartFile mockFile = mock(MultipartFile.class);
    String fileContent = "front1;back1;word1;palavra1\nfront2;back2;word2;palavra2";
    InputStream inputStream = IOUtils.toInputStream(fileContent, "UTF-8");
    when(mockFile.getInputStream()).thenReturn(inputStream);

    ResponseEntity<String> mockResponse = ResponseEntity.ok("success");
    when(ankiClient.addNotes(any())).thenReturn(mockResponse);

    List<Notes> result = ankiService.sendNotesToAnki(mockFile, "DefaultDeck", "BasicModel");

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(ankiClient, times(1)).addNotes(any());
  }

  @Test
  void testSendNotesToAnki_NotFoundException() throws IOException {
    MultipartFile mockFile = mock(MultipartFile.class);
    String fileContent = "front1;back1;word1;palavra1";
    InputStream inputStream = IOUtils.toInputStream(fileContent, "UTF-8");
    when(mockFile.getInputStream()).thenReturn(inputStream);

    ResponseEntity<String> mockResponse = ResponseEntity.ok("not found");
    when(ankiClient.addNotes(any())).thenReturn(mockResponse);

    Exception exception = assertThrows(CommonExceptionNotFound.class,
        () -> ankiService.sendNotesToAnki(mockFile, "DefaultDeck", "BasicModel"));
    assertEquals("Error Not Found deck or model", exception.getMessage());
  }

  @Test
  void testBuildNotes_InvalidInputFormat() {
    MultipartFile mockFile = mock(MultipartFile.class);
    String invalidContent = "front1;back1;word1"; 

    InputStream inputStream = IOUtils.toInputStream(invalidContent, "UTF-8");
    try {
      when(mockFile.getInputStream()).thenReturn(inputStream);
    } catch (IOException e) {
      fail("Mock InputStream failed");
    }

    Exception exception = assertThrows(CommonExceptionBadRequest.class,
        () -> ankiService.sendNotesToAnki(mockFile, "DefaultDeck", "BasicModel"));

    assertTrue(exception.getMessage().contains("Invalid input format"));
  }

  @Test
  void testBuildNotes_FileReadError() throws IOException {
    MultipartFile mockFile = mock(MultipartFile.class);
    when(mockFile.getInputStream()).thenThrow(new IOException("Mocked IO Exception"));

    Exception exception = assertThrows(RuntimeException.class,
        () -> ankiService.sendNotesToAnki(mockFile, "DefaultDeck", "BasicModel"));

    assertTrue(exception.getMessage().contains("Error reading file"));
  }
}
