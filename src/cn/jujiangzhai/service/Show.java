package cn.jujiangzhai.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.jujiangzhai.dao.ICraftDao;
import cn.jujiangzhai.dao.impl.jdbc.ArticleDao;
import cn.jujiangzhai.dao.impl.jdbc.CraftDao;
import cn.jujiangzhai.dao.impl.jdbc.ShopDao;
import cn.jujiangzhai.dao.impl.jdbc.TokenDao;
import cn.jujiangzhai.dao.impl.jdbc.UserDao;
import cn.jujiangzhai.entity.Article;
import cn.jujiangzhai.entity.ArticleListInfo;
import cn.jujiangzhai.entity.DetailsInfo;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.entity.IndexInfo;
import cn.jujiangzhai.entity.Shop;
import cn.jujiangzhai.entity.ShopListInfo;
import cn.jujiangzhai.entity.User;
import cn.jujiangzhai.util.Path;
import cn.jujiangzhai.util.Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/json;charset=utf-8");

		String action = request.getParameter("action");

		CraftDao dao = new CraftDao();
		UserDao userDao = new UserDao();
		ArticleDao articleDao = new ArticleDao();
		ShopDao shopDao = new ShopDao();
		TokenDao tokenDao = new TokenDao();
		if ("getCraftList".equals(action)) {
			List<Handicraft> list = dao.queryAll();
			JSONArray jsonArray = new JSONArray();
			String ps = request.getParameter("pageSize");
			String pn = request.getParameter("pageNow");

			if (ps != null && pn != null) {
				// 每页多少条数据
				int pageSize = Integer.valueOf(ps);
				// 当前第几页
				int pageNow = Integer.valueOf(pn);
				// 共有多少页数据
				int pageCount = 0;
				// 共有多少条数据
				int rowCount = list.size();

				// 计算pageCount
				if (rowCount % pageSize == 0) {
					pageCount = rowCount / pageSize;
				} else {
					pageCount = rowCount / pageSize + 1;
				}

				if (list.size() <= pageSize) {
					if (pageNow == 1) {
						for (Handicraft h : list) {
							jsonArray.add(IndexInfo.fromHc(h));
						}
					}
				} else {
					int fromIndex = (pageNow - 1) * pageSize;
					int toIndex = pageNow * pageSize;
					if (pageNow < pageCount) {
						List<Handicraft> subList = list.subList(fromIndex, toIndex);
						for (Handicraft h : subList) {
							jsonArray.add(IndexInfo.fromHc(h));
						}
					} else if (pageNow == pageCount) {
						List<Handicraft> subList = list.subList(fromIndex, list.size());
						for (Handicraft h : subList) {
							jsonArray.add(IndexInfo.fromHc(h));
						}
					}
				}

			} else { // 没有传入pageSize和pageNow的参数, 返回全部列表

				for (Handicraft h : list) {
					jsonArray.add(IndexInfo.fromHc(h));
				}

			}

			response.getWriter().write(jsonArray.toString());

		} else if ("getCraftDetails".equals(action)) {
			String id = request.getParameter("id");
			Handicraft h = dao.queryById(id);

			DetailsInfo info = DetailsInfo.fromHc(h);
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");

			if (userId != null) {

				User user = userDao.queryById(userId);
				if (user.getRecentViewed().contains(id)) {
					info.setIsViewed(true);
				}
				userDao.view(userId, id);
			}

			JSONArray jsonArray = new JSONArray();
			jsonArray.add(JSONObject.fromObject(info));
			Shop shop = shopDao.queryByName(info.getShopName());
			if (shop != null)
				jsonArray.add(JSONObject.fromObject(ShopListInfo.fromShop(shop)));

			response.getWriter().write(jsonArray.toString());

			// response.getWriter().write(JSONObject.fromObject(info).toString());

		} else if ("getArticleList".equals(action)) {

			List<Article> list = articleDao.queryAll();

			JSONArray jsonArray = new JSONArray();
			String ps = request.getParameter("pageSize");
			String pn = request.getParameter("pageNow");

			if (ps != null && pn != null) {
				// 每页多少条数据
				int pageSize = Integer.valueOf(ps);
				// 当前第几页
				int pageNow = Integer.valueOf(pn);
				// 共有多少页数据
				int pageCount = 0;
				// 共有多少条数据
				int rowCount = list.size();

				// 计算pageCount
				if (rowCount % pageSize == 0) {
					pageCount = rowCount / pageSize;
				} else {
					pageCount = rowCount / pageSize + 1;
				}

				if (list.size() <= pageSize) {
					if (pageNow == 1) {
						for (Article h : list) {
							jsonArray.add(ArticleListInfo.fromArticle(h));
						}
					}
				} else {
					int fromIndex = (pageNow - 1) * pageSize;
					int toIndex = pageNow * pageSize;
					if (pageNow < pageCount) {
						List<Article> subList = list.subList(fromIndex, toIndex);
						for (Article h : subList) {
							jsonArray.add(ArticleListInfo.fromArticle(h));
						}
					} else if (pageNow == pageCount) {
						List<Article> subList = list.subList(fromIndex, list.size());
						for (Article h : subList) {
							jsonArray.add(ArticleListInfo.fromArticle(h));
						}
					}
				}

			} else { // 没有传入pageSize和pageNow的参数, 返回全部列表

				for (Article a : list) {
					jsonArray.add(ArticleListInfo.fromArticle(a));
				}
			}

			response.getWriter().write(jsonArray.toString());

		} else if ("getArticleDetails".equals(action)) {
			String id = request.getParameter("id");
			Article article = articleDao.queryById(id);
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(article);
			List<Handicraft> list2 = dao.queryType(article.getType().trim());
			JSONArray jsonArray2 = new JSONArray();
			for (Handicraft h : list2) {
				jsonArray2.add(IndexInfo.fromHc(h));
			}
			jsonArray.add(jsonArray2);
			response.getWriter().write(jsonArray.toString());

		} else if ("getShopList".equals(action)) {
			List<Shop> list = shopDao.queryAll();
			JSONArray jsonArray = new JSONArray();

			String ps = request.getParameter("pageSize");
			String pn = request.getParameter("pageNow");

			if (ps != null && pn != null) {
				// 每页多少条数据
				int pageSize = Integer.valueOf(ps);
				// 当前第几页
				int pageNow = Integer.valueOf(pn);
				// 共有多少页数据
				int pageCount = 0;
				// 共有多少条数据
				int rowCount = list.size();

				// 计算pageCount
				if (rowCount % pageSize == 0) {
					pageCount = rowCount / pageSize;
				} else {
					pageCount = rowCount / pageSize + 1;
				}

				if (list.size() <= pageSize) {
					if (pageNow == 1) {
						for (Shop h : list) {
							jsonArray.add(ShopListInfo.fromShop(h));
						}
					}
				} else {
					int fromIndex = (pageNow - 1) * pageSize;
					int toIndex = pageNow * pageSize;
					if (pageNow < pageCount) {
						List<Shop> subList = list.subList(fromIndex, toIndex);
						for (Shop h : subList) {
							jsonArray.add(ShopListInfo.fromShop(h));
						}
					} else if (pageNow == pageCount) {
						List<Shop> subList = list.subList(fromIndex, list.size());
						for (Shop h : subList) {
							jsonArray.add(ShopListInfo.fromShop(h));
						}
					}
				}

			} else {

				for (Shop s : list) {
					jsonArray.add(ShopListInfo.fromShop(s));
				}
			}

			response.getWriter().write(jsonArray.toString());

		} else if ("getShopDetails".equals(action)) {
			String id = request.getParameter("id");

			Shop z = shopDao.queryById(id);

			JSONArray list = new JSONArray();

			list.add(z);

			List<Handicraft> list2 = dao.queryByShopName(z.getShopName());

			JSONArray jsonArray = new JSONArray();
			for (Handicraft h : list2) {
				jsonArray.add(IndexInfo.fromHc(h));
			}

			list.add(jsonArray);

			response.getWriter().write(list.toString());

		} else if ("getCraftsByCity".equals(action)) {

			String city = request.getParameter("city");
			city = new String(city.getBytes("iso8859-1"), "utf-8");
			System.out.println(city);
			List<Handicraft> list = dao.queryCity(city);
			JSONArray jsonArray = new JSONArray();
			for (Handicraft h : list) {
				jsonArray.add(IndexInfo.fromHc(h));
			}

			response.getWriter().write(jsonArray.toString());

		} else if ("getCraftsByType".equals(action)) {
			String type = request.getParameter("type");
			type = new String(type.getBytes("iso8859-1"), "utf-8");
			List<Handicraft> list = dao.queryType(type);
			JSONArray jsonArray = new JSONArray();
			for (Handicraft h : list) {
				jsonArray.add(IndexInfo.fromHc(h));
			}
			response.getWriter().write(jsonArray.toString());
		} else if ("getRecommended".equals(action)) {
			List<Handicraft> list = dao.queryRecommend();
			if (list.size() > 2) {
				list = list.subList(0, 2);
			}
			JSONArray jsonArray = new JSONArray();
			for (Handicraft h : list) {
				jsonArray.add(IndexInfo.fromHc(h));
			}
			response.getWriter().write(jsonArray.toString());
		} else if ("getShopByCity".equals(action)) {
			String city = request.getParameter("city");
			city = new String(city.getBytes("iso-8859-1"), "utf-8");
			List<Shop> list = shopDao.queryCity(city);
			JSONArray jsonArray = new JSONArray();
			for (Shop z : list) {
				jsonArray.add(ShopListInfo.fromShop(z));
			}
			response.getWriter().write(jsonArray.toString());
		} else if ("getCollection".equals(action)) {
			int status = 0;
			String token = request.getParameter("token");
			JSONArray result = new JSONArray();
			JSONArray jsonArray = new JSONArray();
			if (token != null) {
				String id = tokenDao.queryByToken(token);
				if (id != null) {
					status = 2;
					User user = userDao.queryById(id);
					List<String> collection = Arrays.asList(user.getCollection().split("#"));

					if (collection != null && collection.size() != 0) {

						for (String itemId : collection) {
							Handicraft hc = dao.queryById(itemId);
							if (hc != null) {
								jsonArray.add(IndexInfo.fromHc(hc));
								status = 1;

							}

						}

					}

				}

			}
			result.add(status);
			result.add(jsonArray);

			response.getWriter().write(result.toString());

		} else if ("getShopByType".equals(action)) {
			String type = request.getParameter("type");
			type = new String(type.getBytes("iso-8859-1"), "utf-8");
			List<Shop> list = shopDao.queryByType(type);
			JSONArray jsonArray = new JSONArray();
			for (Shop z : list) {
				jsonArray.add(ShopListInfo.fromShop(z));
			}
			response.getWriter().write(jsonArray.toString());
		} else if ("getFollowUp".equals(action)) {
			int status = 0;
			String token = request.getParameter("token");
			JSONArray result = new JSONArray();
			JSONArray jsonArray = new JSONArray();
			if (token != null) {
				String id = tokenDao.queryByToken(token);
				if (id != null) {
					status = 2;
					User user = userDao.queryById(id);
					List<String> followUp = Arrays.asList(user.getFollowUp().split("#"));

					if (followUp != null && followUp.size() != 0) {

						for (String itemId : followUp) {
							Shop shop = shopDao.queryById(itemId);
							if (shop != null) {
								jsonArray.add(ShopListInfo.fromShop(shop));
								status = 1;

							}

						}

					}

				}

			}
			result.add(status);
			result.add(jsonArray);

			response.getWriter().write(result.toString());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
