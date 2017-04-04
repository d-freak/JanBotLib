/**
 * AnnounceParam.java
 * 
 * @Author D-freak
 */

package dFreak.project.janbotlib;

import java.util.LinkedList;
import java.util.List;



/**
 * コマンド履歴パラメータ (immutable)
 */
public final class HistoryParam {
    
    /**
     * コンストラクタ
     */
    public HistoryParam(final List<CommandHistory> historyList) {
        _historyList = historyList;
    }
    
    
    
    /**
     * コマンド履歴リストを取得
     */
    public List<CommandHistory> getHistoryList() {
        return _historyList;
    }
    
    
    
    /**
     * コマンド履歴リスト
     */
    private List<CommandHistory> _historyList = new LinkedList<>();
    
}

