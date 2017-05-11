package cn.jujiangzhai.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.omg.CORBA.PRIVATE_MEMBER;

import cn.jujiangzhai.dao.ICraftDao;
import cn.jujiangzhai.dao.impl.jdbc.CraftDao;
import cn.jujiangzhai.entity.Handicraft;
import cn.jujiangzhai.util.Utils;

/**
 * Servlet implementation class AddEntry
 */
@WebServlet("/AddEntry")
public class AddEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "img/handicrafts";

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;

	private static final int MAX_FILE_SIZE = 1024 * 1024 * 5;

	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));

		Handicraft handicraft = null;

		String id = cn.jujiangzhai.util.Utils.getUUID();

		// -----------------------------------------------------

		if (ServletFileUpload.isMultipartContent(request)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();

			factory.setSizeThreshold(MEMORY_THRESHOLD);

			// 设置临时存储目录
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);

			// 设置最大文件上传值
			upload.setFileSizeMax(MAX_FILE_SIZE);

			// 设置最大请求值
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// 中文处理
			upload.setHeaderEncoding("UTF-8");

			// 构造临时路径来存储上传的文件
			// 这个路径相对当前应用的目录
			String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

			// 如果目录不在则创建
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			Map<String, String> params = new HashMap<String, String>();

			try {
				// 解析请求的内容提取文件数据
				@SuppressWarnings("unchecked")
				List<FileItem> formItems = upload.parseRequest(request);

				if (formItems != null && formItems.size() > 0) {
					// 迭代表单数据
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();

							if (fileName != null) {
								String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
								fileName = id + "." + suffix;
								String filePath = uploadPath + File.separator + fileName;
								File storeFile = new File(filePath);
								// 在控制台输出文件的上传路径
								System.out.println(filePath);
								item.write(storeFile);
								request.setAttribute("message", "文件上传成功");
							}

						} else {
							// Field name
							String fieldName = item.getFieldName();

							// Set charset = UTF-8 Default = ISO-8859-1
							// Get field value
							String value = item.getString("utf-8");
							System.out.println(fieldName+":"+value);

							// Put into map
							params.put(fieldName, value.trim());

						}
					}
				}
			} catch (Exception e) {
				request.setAttribute("message", "错误信息:" + e.getMessage());
			}

			handicraft = f1(id, params);

		} else {
			handicraft = f2(id, request);

		}

		// ---------------------保存数据到数据库-----------------------------
		File path = new File(this.getServletContext().getRealPath("/") + "db/handicrafts.xml");

		CraftDao dao = new CraftDao();

		dao.insert(handicraft);

		request.getRequestDispatcher("inputHandicraft.jsp").forward(request, response);
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

	private Handicraft f1(String id, Map<String, String> params) {

		String name = params.get("name");
		
		String type = params.get("type");

		String city = params.get("city");

		String address = params.get("address");

		int weight = Integer.valueOf(params.get("weight"));

		Boolean isRecommended = Boolean.valueOf(params.get("isRecommended"));
		System.out.println(params.get("isRecommended"));
		//System.out.println(isRecommended);
		String cipher = params.get("cipher");

		double discount = Double.valueOf(params.get("discount"));

		String shopName = params.get("shopName");

		String shopLink = params.get("shopLink");

		String description = params.get("description");

		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");

		String date = format.format(new Date());

		return new Handicraft(id, name, type, description, city, address, date, weight, 0, isRecommended, cipher,
				discount, shopName, shopLink);

	}

	private Handicraft f2(String id, HttpServletRequest request) {

		String name = request.getParameter("name");

		String type = request.getParameter("type");

		String city = request.getParameter("city");

		String address = request.getParameter("address");

		int weight = Integer.valueOf(request.getParameter("weight"));

		Boolean isRecommended = Boolean.valueOf(request.getParameter("isRecommended"));

		String cipher = request.getParameter("cipher");

		double discount = Double.valueOf(request.getParameter("discount"));

		String shopName = request.getParameter("shopName");

		String shopLink = request.getParameter("shopLink");

		String description = request.getParameter("description");

		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");

		String date = format.format(new Date());

		return new Handicraft(id, name, type, description, city, address, date, weight, 0, isRecommended, cipher,
				discount, shopName, shopLink);

	}

}
