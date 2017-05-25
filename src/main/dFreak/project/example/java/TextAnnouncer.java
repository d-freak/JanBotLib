/**
 * GameAnnouncer.java
 *
 * @author D-freak
 */

package dFreak.project.example.java;

import wiz.project.jan.JanPai;



/**
 * ゲーム実況者
 */
public class TextAnnouncer extends GameAnnouncer {
    
    /**
     * コンストラクタ
     * 
     * @param configDirPath 設定ファイルパス
     */
    public TextAnnouncer(final String configDirPath) {
        super(configDirPath);
    }
    
    /**
     * 暗槓牌を文字列に変換
     *
     * @param pai 暗槓牌。
     * @return 変換結果。
     */
    @Override
	protected String convertAnkanToString(final JanPai pai) {
        final StringBuilder buf = new StringBuilder();
        final String source = "[■]" + pai + pai + "[■]";
        buf.append(source);
        return buf.toString();
    }
    
    /**
     * 雀牌を文字列に変換
     *
     * @param pai 雀牌。
     * @return 変換結果。
     */
    @Override
    protected String convertJanPaiToString(final JanPai pai) {
        final StringBuilder buf = new StringBuilder();
        buf.append(pai);
        return buf.toString();
    }
    
}

