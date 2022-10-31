package com.controller;

import com.entity.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookServlet extends ViewBaseServlet{
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        librarian: 图书馆管理员
        if (req.getParameter("librarian").equals("book_add")){
            processTemplate("pages/manager/book_add",req,resp);
        }
        if (req.getParameter("librarian").equals("book_edit")){
            String pageNo = req.getParameter("pageNo");
            HttpSession session = req.getSession();
            session.setAttribute("pageNo", pageNo);

            String id = req.getParameter("id");
            Book book = bookService.findAllBookById(Integer.valueOf(id));
            req.setAttribute("book", book);
            processTemplate("pages/manager/book_edit",req,resp);
        }
        if (req.getParameter("librarian").equals("addBook")){
            this.addBook(req, resp);
        }

        if (req.getParameter("librarian").equals("findAllBook")){
            this.findAllBookList(req, resp);
        }
        if (req.getParameter("librarian").equals("deleteBook")){
            this.deleteBook(req, resp);
        }
        if (req.getParameter("librarian").equals("updateBook")){
            this.updateBook(req, resp);
        }
    }

    protected void findAllBookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if (pageNoStr != null){
            pageNo = Integer.parseInt(pageNoStr);
        }
        // 设置会话域
        HttpSession session = req.getSession();
        // pageNo 当前页面数字，设置到会话域中
        session.setAttribute("pageNo", pageNo);
        // 总数据条数
        int bookCount = bookService.findAllBookCount();
        session.setAttribute("bookCount", bookCount);
        // 总页数 (总记录条数 + 显示条数 - 1)/显示条数
        int pageCount = (bookCount + 10 - 1)/10;
        // 页码
        List<Integer> pageList = new ArrayList<>();
        for (int i = 1; i < pageCount + 1; i++) {
            pageList.add(i);
        }
        session.setAttribute("pageList", pageList);
        session.setAttribute("pageCount", pageCount);
        // 分页查询
        List<Book> bookList = bookService.findAllBookList(pageNo);
        req.setAttribute("bookList", bookList);
        processTemplate("/pages/manager/book_manager", req, resp);
    }

    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();

        if(ServletFileUpload.isMultipartContent(req)){
            //创建工厂实现类FileItemFactory
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload=  new ServletFileUpload(fileItemFactory );
            //解析上传的数据，得到每一个表单项FileItem
            try{
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //判断，每一项，是普通类型，还是上传文件
                for(FileItem fileItem : list){
                    if(fileItem.isFormField()){
                        if (fileItem.getFieldName().equals("name")){
                            // 解决乱码 .getString("UTF-8")
                            book.setName(fileItem.getString("UTF-8"));
                        }
                        if (fileItem.getFieldName().equals("price")){
                            book.setPrice(Double.valueOf(fileItem.getString("UTF-8")));
                        }
                        if (fileItem.getFieldName().equals("author")){
                            book.setAuthor(fileItem.getString("UTF-8"));
                        }
                        if (fileItem.getFieldName().equals("sales")){
                            book.setSales(Integer.valueOf(fileItem.getString("UTF-8")));
                        }
                        if (fileItem.getFieldName().equals("stock")){
                            book.setStock(Integer.valueOf(fileItem.getString("UTF-8")));
                        }
                    }else{
                        String fieldName = fileItem.getFieldName();
                        //上传地址  文件名称：fileItem.gitName();
                        System.out.println(fileItem.getName());
                        book.setImgPath("static/uploads/" + fileItem.getName());
                        fileItem.write(new File("E:\\Java_project_2110A\\bookstore_web_test\\web\\static\\uploads\\" + fileItem.getName()));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            int i = bookService.addBookList(book);
            this.findAllBookList(req, resp);
        }
    }

    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageNo = req.getParameter("pageNo");
        int i = bookService.deleteBookList(Integer.parseInt(id));

        // 总数据条数
        int bookCount = bookService.findAllBookCount();
        // 总页数 (总记录条数 + 显示条数 - 1)/显示条数
        int pageCount = (bookCount + 10 - 1)/10;
        if (!(pageCount < 1)){
            if (Integer.parseInt(pageNo) > pageCount){
                resp.sendRedirect("/manage?librarian=findAllBook&pageNo="+(Integer.parseInt(pageNo) - 1));
            }else {
                resp.sendRedirect("/manage?librarian=findAllBook&pageNo="+pageNo);
            }
        }else {
            resp.sendRedirect("/manage?librarian=findAllBook&pageNo=1");
        }
    }

    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        String price = req.getParameter("price");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
        Book book = new Book(Integer.valueOf(bookId), Double.valueOf(price), Integer.valueOf(sales), Integer.valueOf(stock));
        int i = bookService.updateBookList(book);

        HttpSession session = req.getSession();
        Object pageNo = session.getAttribute("pageNo");
        resp.sendRedirect("/manage?librarian=findAllBook&pageNo="+pageNo);
    }
}
