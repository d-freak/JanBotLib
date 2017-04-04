/**
 * AnnounceParam.java
 * 
 * @author D-freak
 */

package dFreak.project.janbotlib;

import java.util.EnumSet;
import java.util.List;

import wiz.project.jan.JanPai;



/**
 * 実況パラメータ (immutable)
 */
public final class AnnounceParam {
    
    /**
     * コンストラクタ
     */
    public AnnounceParam(final AnnounceFlag flag, final List<JanPai> paiList) {
        _flagSet = EnumSet.of(flag);
        _paiList = paiList;
    }
    
    /**
     * コンストラクタ
     */
    public AnnounceParam(final AnnounceFlag flag, final String message) {
        _flagSet = EnumSet.of(flag);
        _message = message;
    }
    
    /**
     * コンストラクタ
     */
    public AnnounceParam(final EnumSet<AnnounceFlag> flagSet) {
        _flagSet = flagSet;
    }
    
    /**
     * コンストラクタ
     */
    public AnnounceParam(final EnumSet<AnnounceFlag> flagSet, final List<JanPai> paiList) {
        _flagSet = flagSet;
        _paiList = paiList;
    }
    
    
    
    /**
     * 実況フラグを取得
     */
    public EnumSet<AnnounceFlag> getFlagSet() {
        return _flagSet;
    }
    
    /**
     * メッセージを取得
     */
    public String getMessage() {
        return _message;
    }
    
    /**
     * 牌リストを取得
     */
    public List<JanPai> getPaiList() {
        return _paiList;
    }
    
    
    
    /**
     * 実況フラグ
     */
    private EnumSet<AnnounceFlag> _flagSet = null;
    
    /**
     * メッセージ
     */
    private String _message = null;
    
    /**
     * 牌リスト
     */
    private List<JanPai> _paiList = null;
    
}

