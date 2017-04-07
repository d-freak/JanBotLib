/**
 * JanFreakMain.java
 */

package dFreak.project.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import dFreak.project.janbotlib.AnnounceFlag;
import dFreak.project.janbotlib.JanBotLib;



public final class JanFreakMain {
    
    public static void main(final String[] paramList) {
        final String configDirPath = paramList[0];  // 記録用XMLなどの置き場
        
        JanBotLib.initialize(new GameAnnouncer());
        
        while (true) {
            try {
                // 閉じてはならない
                final BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
                final String[] commandArray = io.readLine().split(" ", 2);  // 「[プレイヤー名] d ton」の形式
                
                if (commandArray.length != 2) {
                    continue;
                }
                final String playerName = commandArray[0];
                final String message = commandArray[1];
                
                if (message.equals("s") || message.equals("s chm") || message.equals("chm s")) {
                    JanBotLib.startChm(playerName);
                }
                else if (message.equals("s jpm") || message.equals("jpm s")) {
                    JanBotLib.start(playerName);
                }
                else if (message.equals("e") || message.equals("end")) {
                    JanBotLib.end();
                }
                else if (message.equals("d")) {
                    JanBotLib.discardOrContinue();
                }
                else if (message.startsWith("d ")) {
                    JanBotLib.discard(message.substring(2));
                }
                else if (message.equals("u") || message.equals("undo")) {
                    JanBotLib.undo(playerName);
                }
                else if (message.equals("i")) {
                    JanBotLib.info(ANNOUNCE_FLAG_FIELD);
                }
                else if (message.equals("r")) {
                    JanBotLib.info(ANNOUNCE_FLAG_RIVER);
                }
                else if (message.equals("ra")) {
                    JanBotLib.info(ANNOUNCE_FLAG_RIVER_ALL);
                }
                else if (message.equals("i r") || message.equals("r i")) {
                    JanBotLib.info(ANNOUNCE_FLAG_FIELD_AND_RIVER);
                }
                else if (message.equals("i ra")) {
                    JanBotLib.info(ANNOUNCE_FLAG_FIELD_AND_RIVER_ALL);
                }
                else if (message.equals("w")) {
                    JanBotLib.info(ANNOUNCE_FLAG_WATCHING_END);
                }
                else if (message.equals("7th")) {
                    JanBotLib.info(ANNOUNCE_FLAG_SEVENTH);
                }
                else if (message.equals("h")) {
                    JanBotLib.history();
                }
                else if (message.startsWith("chi ")) {
                    JanBotLib.chi(playerName, message.substring(4));
                }
                else if (message.equals("pon")) {
                    JanBotLib.pon(playerName);
                }
                else if (message.startsWith("kan ")) {
                    JanBotLib.kan(playerName, message.substring(4));
                }
                else if (message.equals("hu")) {
                    JanBotLib.hu(playerName);
                }
                else if (message.equals("ron")) {
                    JanBotLib.ron(playerName);
                }
                else if (message.equals("tsumo")) {
                    JanBotLib.tsumo(playerName);
                }
                else if (message.equals("sr")) {
                    JanBotLib.ranking();
                }
                else if (message.equals("ss")) {
                    JanBotLib.statistics(playerName, "");
                }
                else if (message.equals("sy")) {
                    JanBotLib.yaku(playerName, "");
                }
                else if (message.startsWith("ss ")) {
                    JanBotLib.statistics(playerName, message.substring(3));
                }
                else if (message.startsWith("sy ")) {
                    JanBotLib.yaku(playerName, message.substring(3));
                }
                else if (message.startsWith("o ")) {
                    JanBotLib.outs(message.substring(2));
                }
                else if (message.startsWith("w ")) {
                    JanBotLib.watch(message.substring(2));
                }
                else if (message.equals("replay") || message.equals("replay chm") || message.equals("chm replay")) {
                    JanBotLib.replayChm(playerName);
                }
                else if (message.equals("replay jpm") || message.equals("jpm replay")) {
                    JanBotLib.replay(playerName);
                }
                else if (message.equals("help")) {
                    final List<String> messageList =
                        Arrays.asList("ss [X] [開始値-終了値]：指定したプレイヤーのゲーム統計を表示",
                                      "sy [X] [開始値-終了値] [-c表示する役の最大数] [-p表示する役の最小点]：",
                                      "指定したプレイヤーの役のゲーム統計を表示   sr：ランキングを表示",
                                      "※ ss, syはXにa llと指定すると全員分を表示、その場合範囲指定は無効",
                                      "s, s chm：中国麻雀を開始   s jpm：日本麻雀を開始   e：終了",
                                      "replay, replay chm：中国麻雀でリプレイ   replay jpm：日本麻雀でリプレイ",
                                      "i：状態   r：捨て牌   d X：指定牌(ex.9p)を切る (X指定無し：ツモ切り、鳴きキャンセル)",
                                      "chi X：指定牌(ex.3p)を先頭牌としてチー   pon：ポン   kan X：指定牌でカン",
                                      "ron, hu：ロン   tsumo, hu：ツモ和了   u, undo：取り消し   h：コマンド履歴表示",
                                      "ra：他家を含む全ての捨て牌   w：指定牌の残り枚数の自動表示終了",
                                      "w X：指定牌の残り枚数の自動表示(複数指定可) ※ ドラ表示牌はカウント対象外(未実装)",
                                      "o X：指定牌の残り枚数(複数指定可) ※ ドラ表示牌はカウント対象外(未実装)",
                                      "7th：七対モード(手牌に1枚のみの牌の残り枚数を自動表示)切り替え(デフォルトはOFF)");
                    System.out.println(messageList);
                }
                else if (message.equals("chm help")) {
                    final List<String> messageList =
                        Arrays.asList("chm s：中国麻雀を開始   chm replay：中国麻雀でリプレイ");
                    System.out.println(messageList);
                }
                else if (message.equals("jpm help")) {
                    final List<String> messageList =
                        Arrays.asList("jpm s：日本麻雀を開始   jpm replay：日本麻雀でリプレイ");
                    System.out.println(messageList);
                }
                Thread.sleep(100);  // CPUリソース浪費対策
            }
            catch (final Throwable e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    
    
    /**
     * 実況フラグ
     */
    private static final EnumSet<AnnounceFlag> ANNOUNCE_FLAG_FIELD =
        EnumSet.of(AnnounceFlag.FIELD);
    private static final EnumSet<AnnounceFlag> ANNOUNCE_FLAG_RIVER =
        EnumSet.of(AnnounceFlag.RIVER_SINGLE);
    private static final EnumSet<AnnounceFlag> ANNOUNCE_FLAG_RIVER_ALL =
        EnumSet.of(AnnounceFlag.RIVER_ALL);
    private static final EnumSet<AnnounceFlag> ANNOUNCE_FLAG_FIELD_AND_RIVER =
        EnumSet.of(AnnounceFlag.FIELD, AnnounceFlag.RIVER_SINGLE);
    private static final EnumSet<AnnounceFlag> ANNOUNCE_FLAG_FIELD_AND_RIVER_ALL =
        EnumSet.of(AnnounceFlag.FIELD, AnnounceFlag.RIVER_ALL);
    private static final EnumSet<AnnounceFlag> ANNOUNCE_FLAG_WATCHING_END =
        EnumSet.of(AnnounceFlag.WATCHING_END);
    private static final EnumSet<AnnounceFlag> ANNOUNCE_FLAG_SEVENTH =
        EnumSet.of(AnnounceFlag.SEVENTH);
    
}