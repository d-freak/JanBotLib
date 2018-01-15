package dFreak.project.janbotlib;

import java.util.EnumSet;
import java.util.Observer;

/**
 * ライブラリ本体
 */
public final class JanBotLib {
    
    /**
     * コンストラクタ利用禁止
     */
    private JanBotLib() {}
    
    
    
    /**
     * チー
     * 
     * @param playerName プレイヤー名。
     * @param target 先頭牌。
     */
    public static void chi(final String playerName, final String target) {
        JanGameMaster.getInstance().onCallChi(playerName, target);
    }
    
    /**
     * ツモ切り
     */
    public static void discard() {
        JanGameMaster.getInstance().onDiscard();
    }
    
    /**
     * 手出し
     * 
     * @param target 先頭牌。
     */
    public static void discard(final String target) {
        JanGameMaster.getInstance().onDiscard(target);
    }
    
    /**
     * ツモ切りか副露せずに続行
     */
    public static void discardOrContinue() {
        JanGameMaster.getInstance().onDiscardOrContinue();
    }
    
    /**
     * 終了
     */
    public static void end() {
        JanGameMaster.getInstance().onEnd();
    }
    
    /**
     * コマンド履歴表示
     */
    public static void history() {
        JanGameMaster.getInstance().onHistory();
    }
    
    /**
     * フー
     * 
     * @param playerName プレイヤー名。
     */
    public static void hu(final String playerName) {
        JanGameMaster.getInstance().onComplete(playerName);
    }
    
    /**
     * 情報表示
     * 
     * @param observer 実況者
     */
    public static void info(final EnumSet<AnnounceFlag> flagSet) {
        JanGameMaster.getInstance().onInfo(flagSet);
    }
    
    /**
     * 初期化
     * 
     * @param configDirPath 設定保存ディレクトリパス
     * @param observer 実況者
     */
    public static void initialize(final String configDirPath, final Observer observer) {
        JanGameMaster.getInstance().initialize(configDirPath, observer);
    }
    
    /**
     * カン
     * 
     * @param playerName プレイヤー名。
     * @param target 対象牌。
     */
    public static void kan(final String playerName, final String target) {
        JanGameMaster.getInstance().onCallKan(playerName, target);
    }
    
    /**
     * 副露せずに続行
     */
    public static void next() {
        JanGameMaster.getInstance().onContinue();
    }
    
    /**
     * 指定牌の残り枚数表示
     * 
     * @param target 指定牌。
     */
    public static void outs(final String target) {
        JanGameMaster.getInstance().onOuts(target);
    }
    
    /**
     * ポン
     * 
     * @param playerName プレイヤー名。
     */
    public static void pon(final String playerName) {
        JanGameMaster.getInstance().onCallPon(playerName);
    }
    
    /**
     * ランキング表示
     */
    public static void ranking() {
        JanGameMaster.getInstance().onRanking();
    }
    
    /**
     * リプレイ
     */
    public static void replay(final String playerName) {
        JanGameMaster.getInstance().onReplay(playerName);
    }
    
    /**
     * 中国麻雀をリプレイ
     */
    public static void replayChm(final String playerName) {
        JanGameMaster.getInstance().onReplayChm(playerName);
    }
    
    /**
     * ロン
     * 
     * @param playerName プレイヤー名。
     */
    public static void ron(final String playerName) {
        JanGameMaster.getInstance().onCompleteRon(playerName);
    }
    
    /**
     * 開始
     */
    public static void start(final String playerName) {
        JanGameMaster.getInstance().onStart(playerName);
    }
    
    /**
     * 中国麻雀を開始
     */
    public static void startChm(final String playerName) {
        JanGameMaster.getInstance().onStartChm(playerName);
    }
    
    /**
     * ゲーム統計表示
     *
     * @param playerName プレイヤー名。
     */
    public static void statistics(final String playerName) {
        JanGameMaster.getInstance().onStatistics(playerName, "");
    }
    
    /**
     * ゲーム統計表示
     *
     * @param playerName プレイヤー名。
     * @param option オプション。
     */
    public static void statistics(final String playerName, final String option) {
        JanGameMaster.getInstance().onStatistics(playerName, option);
    }
    
    /**
     * テスト
     */
    public static void test(final String playerName) {
        JanGameMaster.getInstance().onTest(playerName);
    }
    
    /**
     * 中国麻雀をテスト
     */
    public static void testChm(final String playerName) {
        JanGameMaster.getInstance().onTestChm(playerName);
    }
    
    /**
     * ツモ
     * 
     * @param playerName プレイヤー名。
     */
    public static void tsumo(final String playerName) {
        JanGameMaster.getInstance().onCompleteTsumo(playerName);
    }
    
    /**
     * 取り消し
     * 
     * @param playerName プレイヤー名。
     */
    public static void undo(final String playerName) {
        JanGameMaster.getInstance().onUndo(playerName);
    }
    
    /**
     * 指定牌の残り枚数自動表示
     * 
     * @param target 指定牌。
     */
    public static void watch(final String target) {
        JanGameMaster.getInstance().onWatch(target);
    }
    
    /**
     * 役のゲーム統計表示
     *
     * @param playerName プレイヤー名。
     */
    public static void yaku(final String playerName) {
        JanGameMaster.getInstance().onYaku(playerName, "");
    }
    
    /**
     * 役のゲーム統計表示
     *
     * @param playerName プレイヤー名。
     * @param option オプション。
     */
    public static void yaku(final String playerName, final String option) {
        JanGameMaster.getInstance().onYaku(playerName, option);
    }
    
}
