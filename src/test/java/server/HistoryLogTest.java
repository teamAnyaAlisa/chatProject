package server;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


/**
 * Created by Java_1 on 01.08.2019.
 */
//@Ignore
//public class HistoryLogTest {
//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void shouldLogCorrectly() throws Exception {
//        HistoryLog logger = new HistoryLog();
//        logger.log("123");
//        logger.log("Привет!");
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        new BufferedInputStream(
//                                new FileInputStream(
//                                        logger.getPath()))));
//        assertEquals("123", in.readLine());
//        assertEquals("Привет!", in.readLine());
//    }
//
//    @Test
//    public void shouldGetHistoryCorrectly() throws Exception {
//        HistoryLog logger = new HistoryLog();
//        logger.log("123 ");
//        logger.log("hello!");
//        assertEquals("123 hello!", logger.getHistory());
//    }
//
//}