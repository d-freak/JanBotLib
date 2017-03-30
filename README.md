# 麻雀BOTライブラリ

Observerインターフェースのupdate()で麻雀のゲーム情報JanInfoと実況フラグAnnounceFlagが通知される。  
これに応じた実装を実施したObserverを引数にしJanBotLib.initialize()を呼び出したのち、  
JanBotLib.start()で日本麻雀が、JanBotLib.startChm()で中国麻雀が開始される。  
  
それ以降の操作はJanBotLib.javaを、また通知タイミングについてはAnnounceFlag.javaを参照のこと。
