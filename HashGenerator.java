package sandbox;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

//ハッシュの生成用クラス
public class HashGenerator {

    //文字コード
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    //アルゴリズム
    private static final String ALGORITHM = "HmacSHA256";


    //本体
    //再代入は考えていない
    private final Mac mac;

    //コンストラクタ
    public HashGenerator() {

        //throwsのせいでtryを書く羽目になったうえ、if分岐とは違いfinalへの初期値の代入ができないため仲介用の変数を作るしかなかった
        Mac mac;
        try {

            //MACの設定
            mac = Mac.getInstance(ALGORITHM);

        } catch (NoSuchAlgorithmException e) {
            mac = null;
            System.out.println(e);
        }

        this.mac = mac;
    }

    //指定の鍵を用いた生成
    public String generate(String raw, String key) {
        try {

            // キーの設定
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(CHARSET), ALGORITHM);
            mac.init(spec);

        } catch (Exception e) {

            System.out.println(e);
        }

        //byte型から直すため、純粋なStringではない
        StringBuilder builder = new StringBuilder();

        //ハッシュ化したバイト列
        byte[] hashed = mac.doFinal(raw.getBytes(CHARSET));

        for (Byte b : hashed) {

            //バイト列を文字列化して末尾に追加する
            builder.append(String.format("%02x", b & 0xff));
        }

        return builder.toString();
    }

}
