package bancoweb.bean;

import bancoweb.dao.ClienteDAO;
import bancoweb.model.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lopes
 */
@WebServlet(name = "ServletCliente", urlPatterns = {"/"})
public class ClienteBean extends HttpServlet {

    private ClienteDAO clienteDAO;

    public void init() {
        clienteDAO = new ClienteDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs &#65039; &#128465;
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/listar":
                    listar(request, response);
                    break;
                case "/editar":
                    mostrar(request, response);
                    break;
                case "/excluir":
                    excluir(request, response);
                    break;
            }

        } catch (IOException | ServletException e) {
            e.printStackTrace();

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getServletPath();

        try {
            switch (action) {
                case "/inserir":
                    inserir(request, response);
                    break;
                case "/editar":
                    editar(request, response);
                    break;
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.listarClientes();
        request.setAttribute("clientes", clientes);
        
        HttpSession session = request.getSession();
        
        String mensagem = (String) session.getAttribute("mensagem");
        session.invalidate();
        
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispacher = request.getRequestDispatcher("clientes.jsp");
        dispacher.forward(request, response);
    }
    
    public void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = new Cliente(request.getParameter("nome"), request.getParameter("sexo").charAt(0),request.getParameter("email"), request.getParameter("civil"), request.getParameter("regiao"));
        String mensagem; 
        int status =  clienteDAO.inserirCliente(cliente);
        
        if(status > 0){
            mensagem = "Registro inserido com sucesso";
        } else {
            mensagem = "Não foi possível inserir o registro";
        }
        request.setAttribute("mensagem", mensagem);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");
        dispatcher.forward(request, response);
    }

    public void mostrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.buscarCliente(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispacher = request.getRequestDispatcher("cliente.jsp");
        dispacher.forward(request, response);
    }
    
    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = new Cliente(request.getParameter("nome"), request.getParameter("sexo").charAt(0),request.getParameter("email"), request.getParameter("civil"), request.getParameter("regiao"));
        int id = Integer.parseInt(request.getParameter("id"));
        cliente.setId(id);
        
        String mensagem; 
        int status = clienteDAO.alterarCliente(cliente);
        
        if(status > 0){
            mensagem = "Registro editado com sucesso";
        } else {
            mensagem = "Não foi possível editar o registro";
        }
        HttpSession session = request.getSession();
        session.setAttribute("mensagem", mensagem);
        response.sendRedirect("listar");
        
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String mensagem; 
        int status = clienteDAO.excluirCliente(id);
        
        if(status > 0){
            mensagem = "Registro excluído com sucesso";
        } else {
            mensagem = "Não foi possível excluir o registro";
        }
        HttpSession session = request.getSession();
        session.setAttribute("mensagem", mensagem);
        response.sendRedirect("listar");
        

    }
}
