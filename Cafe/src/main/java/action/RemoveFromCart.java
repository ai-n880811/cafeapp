package action;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.MenuItem;

@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    	//セッションを取得（ユーザーごとに情報を保持）
        HttpSession session = request.getSession();
        
        //セッションからcart情報（MenuItemのリスト）を取得
        List<MenuItem> cart = (List<MenuItem>) session.getAttribute("cart");

        //カートがnullじゃなかったら（cartがセッションに存在していたら）処理を実行
        if (cart != null) {
            try {
            	
            	// リクエストで受け取った index が有効な範囲（0以上カートのサイズ未満）なら、該当商品を削除
                int index = Integer.parseInt(request.getParameter("index"));
                if (index >= 0 && index < cart.size()) {
                    cart.remove(index);
                }
            } catch (NumberFormatException e) {
            	e.printStackTrace();
            }
        }

        // 更新されたカートをセッションに再セット
        session.setAttribute("cart", cart);

        // カート画面にリダイレクト
        response.sendRedirect("cart.jsp");
    }
}
