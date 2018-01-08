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
 * 中国麻雀コントローラのテスト
 */
public final class ChmJanControllerTest {
    
    @Before
    public void setUp() {
        JanBotLib.initialize("", new TextAnnouncer(""));
        JanBotLib.testChm(PLAYER_NAME);
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
     * ツモ牌の加槓のテスト
     */
    @Test
    public void testAddKanFromTsumo() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發][中]  [３][３][３]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(19);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發] [３]  [３][３][３]
        JanBotLib.kan(PLAYER_NAME, JanPai.SOU_3.toStringWithoutBracket());
        
        assertTrue(!out.toString().equals(""));
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
     * 後付け和絶張のテスト
     */
    @Test
    public void testBackdoorLastTile() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９][中]  [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９] [１]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_9.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][１][２][３][３][４][５] [五]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        discardOrContinue(3);
        // 手牌：[五][⑤][⑦][⑦][１][２][３][３][４][５] [六]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[五][六][⑦][⑦][１][２][３][３][４][５] [６]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_3.toStringWithoutBracket());
        discardOrContinue(7);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        JanBotLib.hu(PLAYER_NAME);
        
        assertTrue(out.toString().contains("---- ロン和了(16巡目) ----" + System.lineSeparator()));
    }
    
    /**
     * 待ち牌変更表示のテスト
     */
    @Test
    public void testChangeWaitingOuts1() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９][中]  [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９] [１]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_9.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][１][２][３][３][４][５] [五]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        discardOrContinue(3);
        // 手牌：[五][⑤][⑦][⑦][１][２][３][３][４][５] [六]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[五][六][⑦][⑦][１][２][３][３][４][５] [６]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_3.toStringWithoutBracket());
        discardOrContinue(5);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        JanBotLib.discardOrContinue();
        
        assertTrue(out.toString().contains("待ち牌：[四]：残り1枚, [七]：残り1枚, 計：残り2枚" + System.lineSeparator()));
    }
    
    /**
     * 待ち牌変更表示のテスト(手出し牌が待ち牌)
     */
    @Test
    public void testChangeWaitingOuts2() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][⑦][２][４][５][９][發][發][中]  [３][３][３]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[④][⑤][⑦][⑦][２][４][５][９][發][發] [八]  [３][３][３]
        JanBotLib.discard(JanPai.SOU_2.toStringWithoutBracket());
        // 手牌：[八][④][⑤][⑦][⑦][４][５][９][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        // 手牌：[八][⑤][⑦][⑦][４][５][９][９][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        discardOrContinue(8);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[八][⑦][⑦][４][５][９][發][發]  [９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.SOU_4.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[八][⑦][⑦][５][９]  [發][發][發] [９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.SOU_5.toStringWithoutBracket());
        // 手牌：[八][⑦][⑦][９] [七]  [發][發][發] [９][９][９] [３][３][３]
        JanBotLib.kan(PLAYER_NAME, JanPai.SOU_9.toStringWithoutBracket());
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[七][八][⑦][⑦] [九]  [發][發][發] [９][９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.MAN_7.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("待ち牌：[七]：残り1枚, 計：残り1枚" + System.lineSeparator()));
    }
    
    /**
     * 待ち牌変更表示のテスト(ツモ切り牌が待ち牌)
     */
    @Test
    public void testChangeWaitingOuts3() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][⑦][２][４][５][９][發][發][中]  [３][３][３]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[④][⑤][⑦][⑦][２][４][５][９][發][發] [八]  [３][３][３]
        JanBotLib.discard(JanPai.SOU_2.toStringWithoutBracket());
        // 手牌：[八][④][⑤][⑦][⑦][４][５][９][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        // 手牌：[八][⑤][⑦][⑦][４][５][９][９][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        discardOrContinue(8);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[八][⑦][⑦][４][５][９][發][發]  [９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.SOU_4.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[八][⑦][⑦][５][９]  [發][發][發] [９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.SOU_5.toStringWithoutBracket());
        // 手牌：[八][⑦][⑦][９] [七]  [發][發][發] [９][９][９] [３][３][３]
        JanBotLib.kan(PLAYER_NAME, JanPai.SOU_9.toStringWithoutBracket());
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[七][八][⑦][⑦] [九]  [發][發][發] [９][９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.MAN_9.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("待ち牌：[九]：残り1枚, 計：残り1枚" + System.lineSeparator()));
    }
    
    /**
     * 8点縛り超え終了のテスト
     */
    @Test
    public void testEndOverTiedPoint() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９][中]  [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９] [１]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_9.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][１][２][３][３][４][５] [五]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        discardOrContinue(3);
        // 手牌：[五][⑤][⑦][⑦][１][２][３][３][４][５] [六]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[五][六][⑦][⑦][１][２][３][３][４][５] [６]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_3.toStringWithoutBracket());
        discardOrContinue(4);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[五][六][⑦][⑦][１][２][３][４][５][６] [③]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_2.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("8点縛り超えが終了しました。" + System.lineSeparator()));
    }
    
    /**
     * 指定牌の残り枚数のテスト
     */
    @Test
    public void testOuts() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.outs(JanPai.PIN_7.toStringWithoutBracket());
        
        assertTrue(out.toString().equals("[⑦]：残り2枚, 計：残り2枚" + System.lineSeparator()));
    }
    
    /**
     * 指定牌の残り枚数のテスト(確認メッセージ)
     */
    @Test
    public void testOutsOnConfirm() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discardOrContinue();
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        JanBotLib.outs(JanPai.PIN_7.toStringWithoutBracket());
        
        assertTrue(out.toString().equals("[⑦]：残り2枚, 計：残り2枚" + System.lineSeparator()));
    }
    
    /**
     * 指定牌の残り枚数のテスト(副露直後)
     */
    @Test
    public void testOutsAfterCall() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[④][⑤][⑦][２][４][５][９][東][發][發][中]  [３][３][３]
        JanBotLib.outs(JanPai.SOU_3.toStringWithoutBracket());
        
        assertTrue(out.toString().equals("[３]：残り1枚, 計：残り1枚" + System.lineSeparator()));
    }
    
    /**
     * 8点縛り超え、待ち牌の残り枚数0枚のテスト
     */
    @Test
    public void testOverTiedPointAndNoOuts() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        discardOrContinue(2);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][中]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_7.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][２][３][３][４][５][９][東][中] [１]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        JanBotLib.chi(PLAYER_NAME, JanPai.SOU_1.toStringWithoutBracket());
        // 手牌：[⑤][２][３][４][５][９][東][中]  [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        discardOrContinue(5);
        // 手牌：[2s][3s][4s][5s][9s][東][中] [7s]  [1s][2s][3s] [發][發][發]
        // 手牌：[２][３][４][５][９][東][中] [７]  [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        discardOrContinue(4);
        JanBotLib.chi(PLAYER_NAME, JanPai.SOU_5.toStringWithoutBracket());
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[２][３][４][９][中]  [５][６][７] [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("8点縛りを超えていますが、和了牌がありません。" + System.lineSeparator()));
    }
    
    /**
     * 8点縛り超え、残り枚数0枚の待ち牌を表示しないテスト
     */
    @Test
    public void testOverTiedPointWithNoOuts1() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        discardOrContinue(2);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][中]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_7.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][２][３][３][４][５][９][東][中] [１]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        discardOrContinue(6);
        // 手牌：[⑤][１][２][３][３][４][５][９][東][中] [６]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[１][２][３][３][４][５][６][９][東][中] [２]  [發][發][發]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        // 手牌：[１][２][２][３][３][４][５][６][９][中] [白]  [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(2);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[１][２][２][３][３][４][５][６][９][白] [白]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_9.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("待ち牌：[４]：残り3枚, [７]：残り3枚, 計：残り6枚" + System.lineSeparator()));
    }
    
    /**
     * 8点縛り超え、残り枚数0枚の待ち牌を表示しないテスト
     */
    @Test
    public void testOverTiedPointWithNoOuts2() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        discardOrContinue(2);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][中]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_7.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][２][３][３][４][５][９][東][中] [１]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        discardOrContinue(6);
        // 手牌：[⑤][１][２][３][３][４][５][９][東][中] [６]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[１][２][３][３][４][５][６][９][東][中] [２]  [發][發][發]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        // 手牌：[１][２][２][３][３][４][５][６][９][中] [白]  [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[１][２][２][３][３][４][５][６][９][白] [白]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_9.toStringWithoutBracket());
        discardOrContinue(5);
        JanBotLib.chi(PLAYER_NAME, JanPai.SOU_1.toStringWithoutBracket());
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[２][３][３][４][５][６][白][白]  [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.SOU_6.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("待ち牌：[４]：残り1枚, 計：残り1枚" + System.lineSeparator()));
    }
    
    /**
     * 和了可能牌リストの更新のテスト
     */
    @Test
    public void testUpdateCompletableJanPaiList() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        discardOrContinue(2);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][中]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_7.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][２][３][３][４][５][９][東][中] [１]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        JanBotLib.chi(PLAYER_NAME, JanPai.SOU_1.toStringWithoutBracket());
        // 手牌：[⑤][２][３][４][５][９][東][中]  [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        discardOrContinue(5);
        // 手牌：[２][３][４][５][９][東][中] [７]  [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        discardOrContinue(9);
        // 手牌：[２][３][４][５][７][９][中] [３]  [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[２][３][３][４][５][７][９] [４]  [１][２][３] [發][發][發]
        JanBotLib.discard(JanPai.SOU_9.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("待ち牌：[７]：残り2枚, 計：残り2枚" + System.lineSeparator()));
    }
    
    /**
     * 待ち牌表示のテスト
     */
    @Test
    public void testWaitingOuts() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９][中]  [發][發][發]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][２][３][３][４][５][９] [１]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_9.toStringWithoutBracket());
        discardOrContinue(2);
        // 手牌：[④][⑤][⑦][⑦][１][２][３][３][４][５] [五]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        discardOrContinue(3);
        // 手牌：[五][⑤][⑦][⑦][１][２][３][３][４][５] [六]  [發][發][發]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[五][六][⑦][⑦][１][２][３][３][４][５] [６]  [發][發][發]
        JanBotLib.discard(JanPai.SOU_3.toStringWithoutBracket());
        discardOrContinue(3);
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        JanBotLib.discardOrContinue();
        
        assertTrue(out.toString().contains("待ち牌：[四]：残り1枚, 計：残り1枚" + System.lineSeparator()));
    }
    
    /**
     * 待ち牌表示のテスト(槓直後)
     */
    @Test
    public void testWaitingOutsAfterKan() throws Exception {
        // 手牌：[④][⑤][⑦][２][３][３][４][５][９][東][發][發][中] [⑦]
        JanBotLib.discard(JanPai.TON.toStringWithoutBracket());
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[④][⑤][⑦][⑦][２][４][５][９][發][發][中]  [３][３][３]
        JanBotLib.discard(JanPai.CHUN.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        // 手牌：[④][⑤][⑦][⑦][２][４][５][９][發][發] [八]  [３][３][３]
        JanBotLib.discard(JanPai.SOU_2.toStringWithoutBracket());
        // 手牌：[八][④][⑤][⑦][⑦][４][５][９][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_4.toStringWithoutBracket());
        // 手牌：[八][⑤][⑦][⑦][４][５][９][９][發][發] [９]  [３][３][３]
        JanBotLib.discard(JanPai.PIN_5.toStringWithoutBracket());
        discardOrContinue(8);
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[八][⑦][⑦][４][５][９][發][發]  [９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.SOU_4.toStringWithoutBracket());
        JanBotLib.discardOrContinue();
        JanBotLib.pon(PLAYER_NAME);
        // 手牌：[八][⑦][⑦][５][９]  [發][發][發] [９][９][９] [３][３][３]
        JanBotLib.discard(JanPai.SOU_5.toStringWithoutBracket());
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // 手牌：[八][⑦][⑦][９] [七]  [發][發][發] [９][９][９] [３][３][３]
        JanBotLib.kan(PLAYER_NAME, JanPai.SOU_9.toStringWithoutBracket());
        
        assertTrue(out.toString().contains("待ち牌：[六]：残り2枚, [九]：残り2枚, 計：残り4枚" + System.lineSeparator()));
    }
    
    
    
    private void discardOrContinue(final int count) {
        for (int i = 0; i < count; i++) {
            JanBotLib.discardOrContinue();
        }
    }
    
    
    
    private static final String PLAYER_NAME = "masasutzu";
    
    private final PrintStream printStream = System.out;
    
}
