package bean;

public class MenuItem {
	
	//商品コード（0～）
    private int id;
    
    //商品名
    private String name;
    
    //商品価格
    private int price;

    
	// コンストラクタで受け取った値を各フィールドにセットする
    public MenuItem(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //商品コードの取得
    public int getId() {
        return id;
    }

    //商品名の取得
    public String getName() {
        return name;
    }

    //商品価格の取得
    public int getPrice() {
        return price;
    }
}
