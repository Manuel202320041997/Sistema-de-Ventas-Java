package Controller;

import java.util.ArrayList;
import java.util.List;

import DaoImpl.ClienteDaoImpl;
import Model.Cliente;
import Model.Usuario;

public class ClienteController {
	
	private ClienteDaoImpl clienteDaoImpl = null;
    
    public ClienteController(){
        clienteDaoImpl = new ClienteDaoImpl();
    }
    
    public List<Cliente> listarCliente(){
    	List<Cliente> listaCompleta = clienteDaoImpl.listarCliente();
    	List<Cliente> listaFiltrada = new ArrayList<>();
    	
    	for (Cliente cliente : listaCompleta) {
            if (cliente.getEstado()) {//TRAE A TODOS LOS OBJETOS O FILAS DE DATOS QUE HAY EN LISTA COMPLETA, PERO SOLO ADMITE LOS QUE TIENEN DE ESTADO (TRUE)
                listaFiltrada.add(cliente);
            }
        }
    	return listaFiltrada;
    }
    
    public void agregarCliente(Cliente cliente) {
    	try {
   	        // Crear un objeto Alumno con los valores numéricos
   	     
   	        clienteDaoImpl.agregarCliente(cliente);
   	    } catch (Exception e) {
   	        e.printStackTrace(); // Manejo de excepciones, puedes personalizarlo según tus necesidades
   	    }
    }
    
    public void eliminarCliente(int idCliente) {
    	clienteDaoImpl.eliminarCliente(idCliente);
    }

    public String buscarClientePorId(int idCliente){
        return clienteDaoImpl.buscarClientePorId(idCliente);
    }

    public int obtenerIdClientePorNombre(String nombreCliente){
		return clienteDaoImpl.obtenerIdClientePorNombre(nombreCliente);
   }


}
