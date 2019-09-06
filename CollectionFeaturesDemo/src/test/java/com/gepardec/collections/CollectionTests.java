package com.gepardec.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CollectionTests {

    private List<String> unmodifiableList;
    private List<String> list;
    private List<String> copiedListUnmodifiable;
    private List<String> copiedList;
    private List<String> arrayList;
    private List<String> originalList;


    @Before
    public void setup() {

        originalList = new ArrayList<>();
        originalList.add("Hello");
        originalList.add("World");
        originalList.add("this");
        originalList.add("is");
        originalList.add("blubb");

        unmodifiableList = Collections.unmodifiableList(originalList);
        list = List.of("Hello", "World", "this", "is", "blubb");
        copiedListUnmodifiable = Collections.unmodifiableList(List.copyOf(originalList));
        copiedList = List.copyOf(originalList);
        arrayList = Arrays.asList("Hello", "World", "this", "is", "blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddList() {

        list.add("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddUnmodifiableList() {

        unmodifiableList.add("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddCopiedList() {

        copiedList.add("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddArrayList() {

        arrayList.add("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveList() {

        list.remove("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveUnmodifiableList() {

        unmodifiableList.remove("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveCopiedList() {

        copiedList.remove("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveArrayList() {

        arrayList.remove("blubb");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetList() {

        list.set(1, "zup");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetUnmodifiableList() {

        unmodifiableList.set(1, "zup");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetCopiedList() {

        copiedList.set(1, "zup");
    }

    @Test
    public void testSetArrayList() {

        arrayList.set(1, "zup");
        assertEquals(arrayList.size(), 5);
    }

    @Test
    public void testOriginalListModifiesCopiedList(){
        originalList.add("blaaaaaaaa");
        assertEquals(originalList.size(), copiedList.size()+1);
        assertEquals(originalList.size(), unmodifiableList.size());
        assertEquals(originalList.size(), arrayList.size()+1);
        assertEquals(originalList.size(), copiedListUnmodifiable.size()+1);
    }

    @Test
    public void testAenderungOriginalListe(){

    }


}
