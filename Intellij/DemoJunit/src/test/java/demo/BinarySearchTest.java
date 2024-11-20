package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BinarySearchTest {

    @Test
    void singleFound() {
        BinarySearch bs = new BinarySearch();
        int[] arr = {7};
        demo.BinarySearch.Result r = bs.search(7, arr);
        assertEquals(true, r.Found);
        assertEquals(0, r.index);
    }

    @Test
    void singleNotFound() {
        BinarySearch bs = new BinarySearch();
        int[] arr = {8};
        demo.BinarySearch.Result r = bs.search(7, arr);
        assertEquals(false, r.Found);
//        assertEquals(0, r.index);
    }

    @Test
    void multipleFirst() {
        BinarySearch bs = new BinarySearch();
        int[] arr = {7, 9, 11};
        demo.BinarySearch.Result r = bs.search(7, arr);
        assertEquals(true, r.Found);
        assertEquals(0, r.index);
    }

    @Test
    void multipleMiddle() {
        BinarySearch bs = new BinarySearch();
        int[] arr = {7, 9, 11};
        demo.BinarySearch.Result r = bs.search(9, arr);
        assertEquals(true, r.Found);
        assertEquals(1, r.index);
    }

    @Test
    void multipleLast() {
        BinarySearch bs = new BinarySearch();
        int[] arr = {7, 9, 11};
        demo.BinarySearch.Result r = bs.search(11, arr);
        assertEquals(true, r.Found);
        assertEquals(2, r.index);
    }

    @Test
    void emptyArray() {
        BinarySearch bs = new BinarySearch();
        int[] arr = {};
        demo.BinarySearch.Result r = bs.search(11, arr);
        assertEquals(false, r.Found);
//        assertEquals(2, r.index);
    }

}