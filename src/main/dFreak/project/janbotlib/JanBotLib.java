package dFreak.project.janbotlib;

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
     * ツモ切り
     */
    public static void end() {
        JanGameMaster.getInstance().onEnd();
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
     * 初期化処理
     * 
     * @param observer 実況者
     */
    public static void initialize(final Observer observer) {
        JanGameMaster.getInstance().initialize(observer);
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
     * ポン
     * 
     * @param playerName プレイヤー名。
     */
    public static void pon(final String playerName) {
        JanGameMaster.getInstance().onCallPon(playerName);
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
     * ツモ
     * 
     * @param playerName プレイヤー名。
     */
    public static void tsumo(final String playerName) {
        JanGameMaster.getInstance().onCompleteTsumo(playerName);
    }
    
}
