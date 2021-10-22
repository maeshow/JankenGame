## JankenGame

じゃんけんゲーム

## Overview

コンピュータとのじゃんけん勝負

## Detail

- グー -> 0, チョキ -> 1, パー -> 2
- あいこの場合決着がつくまで繰り返す

### インターフェース

#### CUI

実行例： 勝ち

``` console
じゃんけん勝負
グーチョキパーを数字で入力してね
0:グー
1:チョキ
2:パー
最初はぐー、じゃんけん：1
パー(COM)とチョキ(Player)で…
あなたの勝ち
```

実行例： 負け

``` console
じゃんけん勝負
グーチョキパーを数字で入力してね
0:グー
1:チョキ
2:パー
最初はぐー、じゃんけん：1
グー(COM)とチョキ(Player)で…
あなたの負け
```

実行例： あいこ

``` console
じゃんけん勝負
グーチョキパーを数字で入力してね
0:グー
1:チョキ
2:パー
最初はぐー、じゃんけん：2
パー(COM)とパー(Player)で…
あいこだよ！
あいこで：1
グー(COM)とチョキ(Player)で…
あなたの負け
```

## Structure Overview

- src/
    - App
        - String[] HAND
        - main()
        - getComputerHand()
        - getPlayerHand()
        - onGameStart()
        - isDraw(int a, int b)
        - isPlayerWin(int a, int b)
    - Messages