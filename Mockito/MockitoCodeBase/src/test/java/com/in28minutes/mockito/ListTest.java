package com.in28minutes.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// This class mocks the List Interface.
public class ListTest {

    @Test
    public void letsMockListSize() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        assertEquals(10, list.size());
    }

    @Test
    public void letsMockListSizeWithMultipleReturnValues() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, list.size()); // First Call
        assertEquals(20, list.size()); // Second Call should return 20
    }

    @Test
    public void letsMockListGet() {
        List<String> list = mock(List.class);
        when(list.get(0)).thenReturn("in28Minutes");
        assertEquals("in28Minutes", list.get(0));
        assertNull(list.get(1));
    }


    @Test(expected = RuntimeException.class)
    public void letsMockListGetToThrowException() {
        List<String> list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Something went wrong"));
        list.get(0);
    }

    @Test
    public void letsMockListGetWithAny() {
        List<String> list = mock(List.class);
        Mockito.when(list.get(anyInt())).thenReturn("in28Minutes");
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        assertEquals("in28Minutes", list.get(0));
        assertEquals("in28Minutes", list.get(1));
    }

    // Using BDD
    @Test
    public void bddAliases_UsingGivenWillReturn() {
        List<String> list = mock(List.class);

        //given
        given(list.get(anyInt())).willReturn("in28Minutes");

        //then
        assertThat("in28Minutes", is(list.get(0)));
        assertThat("in28Minutes", is(list.get(0)));
    }

    @Test
    public void letsMockListSizeUsingBDD() {
        // Given
        List<Integer> list = mock(List.class);
        when(list.size()).thenReturn(10);

        // When
        int size = list.size();

        // Then
        assertThat(size, is(10));
    }
}
