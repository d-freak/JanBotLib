package dFreak.project.janbotlib.game;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dFreak.project.example.java.TextAnnouncer;
import dFreak.project.janbotlib.JanBotLib;
import wiz.project.jan.JanPai;



/**
 * 麻雀コントローラのテスト
 */
public final class SoloJanControllerTest {
    
    @Before
    public void setUp() {
        JanBotLib.initialize("", new TextAnnouncer(""));
        JanBotLib.test(PLAYER_NAME);
    }
    
    @After
    public void after() {
        System.setOut(printStream);
        JanBotLib.end();
    }
    
    
    
    /**
     * 加槓のテスト
     */
    @Test
    public void testAddKan() {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發][中]  [３][３][３]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發] [八]  [３][３][３]
        JanBotLib.kan(PLAYER_NAME, JanPai.SOU_3.toStringWithoutBracket());
        
        assertTrue(out.toString().equals(""));
    }
    
    /**
     * ポン直後の加槓のテスト
     */
    @Test
    public void testAddKanAfterCall() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發][中]  [３][３][３]
        JanBotLib.kan(PLAYER_NAME, JanPai.SOU_3.toStringWithoutBracket());
        
        assertTrue(out.toString().equals(""));
    }
    
    /**
     * 手牌の加槓のテスト
     */
    @Test
    public void testAddKanFromHand() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發][中]  [３][３][３]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        // 手牌：[⑤][⑦][２][４][５][９][９][東][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        discardOrContinue(7);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[⑦][２][４][５][９][東][發][發]  [９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[⑦][２][４][５][９][發][發] [四]  [９][９][９] [３][３][３]
        JanBotLib.kan(PLAYER_NAME, JanPai.SOU_9.toStringWithoutBracket());
        
        assertTrue(!out.toString().equals(""));
    }
    
    /**
     * 取り消しのテスト
     */
    @Test
    public void testUndo() {
        final ByteArrayOutputStream expect = new ByteArrayOutputStream();
        System.setOut(new PrintStream(expect));
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discardOrContinue();
        
        System.setOut(printStream);
        
        JanBotLib.pon(PLAYER_NAME);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發][中]  [３][３][３]
        JanBotLib.undo(PLAYER_NAME);
        
        assertTrue(out.toString().equals(expect.toString()));
    }
    
    
    
    private void discardOrContinue(final int count) {
        for (int i = 0; i < count; i++) {
            JanBotLib.discardOrContinue();
        }
    }
    
    
    
    private static final String PLAYER_NAME = "masasutzu";
    
    private final PrintStream printStream = System.out;
    
}
