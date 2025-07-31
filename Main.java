package sandbox;

import java.util.Date;

public class Main {

	//セッションの長さ(ミリ秒単位)
	//とりあえず1時間にしている
	private static final long SESSION_LENGTH = 1000 * 60 * 60;
	
	//ここでは、例としてユーザ名とセッション終了時刻からハッシュを生成している
	public static void main(String[] args) {
		
		//ユーザ名
		String name = "LucKee";
		
		//セッションの終了時刻
		Long expire = new Date().getTime() + SESSION_LENGTH;
		
		/*上記さえ同値であれば、必ず同じセッションIDが生成される*/
		
		//時刻を0埋めした16進数に直す
		String hexpire = String.format("%016x", expire);
		
		//ハッシュ生成用のクラス
		//演習で自作したものを流用している
		HashGenerator hash = new HashGenerator();

		//セッションID
		String sessionId = hash.generate(name, hexpire);
		
		
		//以下の3つが、セッション情報として必要となる
		//期限内かを検証した後、同手段でIDを生成して一致すれば正しいセッションとして扱う
		System.out.println(name);
		System.out.println(hexpire);
		System.out.println(sessionId);
		
	}

}
