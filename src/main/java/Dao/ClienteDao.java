package Dao;

import java.util.List;
import DaoImpl.ClienteDaoImpl;
import Model.Cliente;

public interface ClienteDao {
	public List<Cliente> listarCliente();
	
	public void agregarCliente(Cliente cliente);
	
	public void eliminarCliente(int idCliente);

	public String buscarClientePorId(int idCliente);

	public int obtenerIdClientePorNombre(String nombreCliente);
}
