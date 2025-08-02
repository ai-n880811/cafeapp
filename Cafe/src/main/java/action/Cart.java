package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.MenuItem; 

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Cart() {
        super();
    }

    /**
     * POSTリクエストを処理するメソッド
     * フォームから送られた商品番号(itemId)を受け取り、
     * セッションのカートに商品を追加するか、
     * 注文終了の場合は注文結果画面へ遷移する。
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームから送られたitemIdを取得（文字列をintに変換）
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        // 商品のマップ（商品ID → MenuItemオブジェクト）
        Map<Integer, MenuItem> menu = Map.of(
            1, new MenuItem(1, "カフェラテ", 450),
            2, new MenuItem(2, "アールグレイ", 400),
            3, new MenuItem(3, "チーズケーキ", 500),
            4, new MenuItem(4, "ガトーショコラ", 550)
        );

        // セッションを取得（ユーザーごとに情報を保持）
        HttpSession session = request.getSession();

        // セッションからカート情報を取得。なければ新しく作る。
        List<MenuItem> cart = (List<MenuItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // itemIdが0なら注文終了なので注文結果ページへリダイレクト
        if (itemId == 0) {
            session.setAttribute("cart", cart);  // カートの状態をセッションに保存
            response.sendRedirect("orderResult.jsp"); // 注文結果画面へ
            return;  // ここで処理終了
        }

        // 選択された商品IDに対応するMenuItemを取得
        MenuItem item = menu.get(itemId);

        // 有効な商品が選ばれていればカートに追加し、メッセージを作成
        if (item != null) {
            cart.add(item); // カートに追加
            session.setAttribute("message", item.getName() + "をカートに追加しました"); // メッセージをセット
        } else {
            // 商品IDが無効な場合のメッセージ
            session.setAttribute("message", "無効な商品番号です");
        }

        // カート情報をセッションに保存
        session.setAttribute("cart", cart);

        // メニュー画面にリダイレクトして処理終了
        response.sendRedirect("menu.jsp");
    }
}
