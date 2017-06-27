package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GuardarXML {

	public void crearXMLsaveA(String agencia1) {
		try {
			System.out.println("Crear users");
			Document Agencias = new Document();
			Element agencias = new Element("Agencias");
			Agencias.setRootElement(agencias);

			Element agencia = new Element("Agencia");
			Element nombre = new Element("Nombre");

			nombre.addContent(new Text(agencia1));

			agencia.addContent(nombre);

			agencias.addContent(agencia);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(Agencias, new FileOutputStream(new File(
					"C:\\temp\\agenciasG.xml")));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void guardarA(String agencia1) {
		System.out.println("Guardar users");
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("C:\\temp\\agenciasG.xml");

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			Element agencia = new Element("Agencia");
			Element nombre = new Element("Nombre");

			nombre.addContent(new Text(agencia1));

			agencia.addContent(nombre);
			rootNode.addContent(agencia);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(document, new FileOutputStream(xmlFile));
		} catch (JDOMException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}

	public void crearXMLUsuario(String nombre1, String nick, String pass,
			String tipo1, String agencia1) {
		try {
			System.out.println("Crear users");
			Document Usuario = new Document();
			Element usuarios = new Element("usuarios");
			Usuario.setRootElement(usuarios);

			Element usuario = new Element("usuario");
			Element nombre = new Element("nombre");
			Element nickname = new Element("nickname");
			Element contrasena = new Element("contrasena");
			Element tipo = new Element("tipo");
			Element agencia = new Element("agencia");

			nombre.addContent(new Text(nombre1));
			nickname.addContent(new Text(nick));
			contrasena.addContent(new Text(pass));
			tipo.addContent(new Text(tipo1));
			agencia.addContent(new Text(agencia1));

			usuario.addContent(nombre);
			usuario.addContent(nickname);
			usuario.addContent(contrasena);
			usuario.addContent(tipo);
			usuario.addContent(agencia);

			usuarios.addContent(usuario);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(Usuario, new FileOutputStream(new File(
					"C:\\temp\\users.xml")));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void crearXMLCliente(String nombre1, String apellido1, String nit1,
			String correo1) {
		try {
			System.out.println("Crear clientes");
			Document Cliente = new Document();
			Element clientes = new Element("clientes");
			Cliente.setRootElement(clientes);

			Element cliente = new Element("cliente");
			Element nombre = new Element("nombre");
			Element apellido = new Element("apellido");
			Element nit = new Element("nit");
			Element correo = new Element("correo");

			nombre.addContent(new Text(nombre1));
			apellido.addContent(new Text(apellido1));
			nit.addContent(new Text(nit1));
			correo.addContent(new Text(correo1));

			cliente.addContent(nombre);
			cliente.addContent(apellido);
			cliente.addContent(nit);
			cliente.addContent(correo);

			clientes.addContent(cliente);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(Cliente, new FileOutputStream(new File(
					"C:\\temp\\clientes.xml")));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void crearXMLVenta(String vendedo, String client, String total,
			String fech, String factura1, String marca, String modelo,
			String precio, String anio, String color) {
		try {
			System.out.println("Crear ventas");
			Document Ventas = new Document();
			Element ventas = new Element("ventas");
			Ventas.setRootElement(ventas);

			Element Venta = new Element("venta");
			Element vendedor = new Element("vendedor");
			Element cliente = new Element("cliente");
			Element totalventa = new Element("totalventa");
			Element fecha = new Element("fecha");
			Element factura = new Element("factura");
			Element vehiculo = new Element("Vehiculo");
			Element Marca = new Element("Marca");
			Element Anio = new Element("Anio");
			Element Modelo = new Element("Modelo");
			Element Color = new Element("Color");
			Element Precio = new Element("Precio");

			Marca.addContent(new Text(marca));
			Anio.addContent(new Text(anio));
			Modelo.addContent(new Text(modelo));
			Color.addContent(new Text(color));
			Precio.addContent(new Text(precio));

			vehiculo.addContent(Marca);
			vehiculo.addContent(Anio);
			vehiculo.addContent(Modelo);
			vehiculo.addContent(Color);
			vehiculo.addContent(Precio);

			vendedor.addContent(new Text(vendedo));
			cliente.addContent(new Text(client));
			totalventa.addContent(new Text(total));
			fecha.addContent(new Text(fech));
			factura.addContent(new Text(factura1));

			Venta.addContent(vendedor);
			Venta.addContent(cliente);
			Venta.addContent(totalventa);
			Venta.addContent(fecha);
			Venta.addContent(factura);
			Venta.addContent(vehiculo);

			ventas.addContent(Venta);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(Ventas, new FileOutputStream(new File(
					"C:\\temp\\ventas.xml")));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void crearXMLVendedor(String id, String name, String pass,
			String lastn, String agencia1) {
		try {
			System.out.println("Crear vendedor");
			Document Vendedores = new Document();
			Element vendedores = new Element("vendedores");
			Vendedores.setRootElement(vendedores);

			Element Vendedor = new Element("Vendedor");
			Element Identificador = new Element("Identificador");
			Element Nombre = new Element("Nombre");
			Element Contrasenia = new Element("Contrasenia");
			Element Apellido = new Element("Apellido");
			Element Agencia = new Element("Agencia");

			Identificador.addContent(new Text(id));
			Nombre.addContent(new Text(name));
			Contrasenia.addContent(new Text(pass));
			Apellido.addContent(new Text(lastn));
			Agencia.addContent(new Text(agencia1));

			Vendedor.addContent(Nombre);
			Vendedor.addContent(Apellido);
			Vendedor.addContent(Contrasenia);
			Vendedor.addContent(Identificador);
			Vendedor.addContent(Agencia);

			vendedores.addContent(Vendedor);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(Vendedores, new FileOutputStream(new File(
					"C:\\temp\\vendedores.xml")));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void guardarCliente(String nombre1, String apellido1, String nit1,
			String corre) {
		System.out.println("Guardar users");
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("C:\\temp\\clientes.xml");

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			Element cliente = new Element("cliente");
			Element nombre = new Element("nombre");
			Element apellido = new Element("apellido");
			Element nit = new Element("nit");
			Element correo = new Element("correo");

			nombre.addContent(new Text(nombre1));
			apellido.addContent(new Text(apellido1));
			nit.addContent(new Text(nit1));
			correo.addContent(new Text(corre));

			cliente.addContent(nombre);
			cliente.addContent(apellido);
			cliente.addContent(nit);
			cliente.addContent(correo);

			rootNode.addContent(cliente);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(document, new FileOutputStream(xmlFile));

		} catch (JDOMException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}

	public void guardarVendedor(String nombre1, String ape, String id,
			String agencia1, String pass) {
		System.out.println("Guardar vendedor");
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("C:\\temp\\vendedores.xml");

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			Element Vendedor = new Element("Vendedor");
			Element Nombre = new Element("Nombre");
			Element Apellido = new Element("Apellido");
			Element Contrasenia = new Element("Contrasenia");
			Element Identificador = new Element("Identificador");
			Element Agencia = new Element("Agencia");

			Nombre.addContent(new Text(nombre1));
			Apellido.addContent(new Text(ape));
			Identificador.addContent(new Text(id));
			Agencia.addContent(new Text(agencia1));
			Contrasenia.addContent(new Text(pass));

			Vendedor.addContent(Identificador);
			Vendedor.addContent(Nombre);
			Vendedor.addContent(Apellido);
			Vendedor.addContent(Contrasenia);
			Vendedor.addContent(Agencia);

			rootNode.addContent(Vendedor);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(document, new FileOutputStream(xmlFile));

		} catch (JDOMException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}

	public void guardarVentas(String vendedo, String client, String total,
			String fech, String factura1, String marca, String modelo,
			String precio, String anio, String color) {
		System.out.println("Guardar ventas");
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("C:\\temp\\ventas.xml");

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			Element venta = new Element("venta");
			Element vendedor = new Element("vendedor");
			Element cliente = new Element("cliente");
			Element totalventa = new Element("totalventa");
			Element fecha = new Element("fecha");
			Element factura = new Element("factura");
			Element vehiculo = new Element("vehiculo");
			Element Marca = new Element("Marca");
			Element Anio = new Element("Anio");
			Element Modelo = new Element("Modelo");
			Element Color = new Element("Color");
			Element Precio = new Element("Precio");

			Marca.addContent(new Text(marca));
			Anio.addContent(new Text(anio));
			Modelo.addContent(new Text(modelo));
			Color.addContent(new Text(color));
			Precio.addContent(new Text(precio));

			vehiculo.addContent(Marca);
			vehiculo.addContent(Anio);
			vehiculo.addContent(Modelo);
			vehiculo.addContent(Color);
			vehiculo.addContent(Precio);

			vendedor.addContent(new Text(vendedo));
			cliente.addContent(new Text(client));
			totalventa.addContent(new Text(total));
			fecha.addContent(new Text(fech));
			factura.addContent(new Text(factura1));

			venta.addContent(vendedor);
			venta.addContent(cliente);
			venta.addContent(totalventa);
			venta.addContent(fecha);
			venta.addContent(factura);
			venta.addContent(vehiculo);

			rootNode.addContent(venta);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(document, new FileOutputStream(xmlFile));

		} catch (JDOMException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}

	public void guardarUsuarios(String nombre1, String nick, String pass,
			String agencia1, String tipo1) {
		System.out.println("Guardar users");
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("C:\\temp\\users.xml");

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			Element usuario = new Element("usuario");
			Element nombre = new Element("nombre");
			Element nickname = new Element("nickname");
			Element contrasena = new Element("contrasena");
			Element tipo = new Element("tipo");
			Element agencia = new Element("agencia");

			nombre.addContent(new Text(nombre1));
			nickname.addContent(new Text(nick));
			contrasena.addContent(new Text(pass));
			tipo.addContent(new Text(tipo1));
			agencia.addContent(new Text(agencia1));

			usuario.addContent(nombre);
			usuario.addContent(nickname);
			usuario.addContent(contrasena);
			usuario.addContent(tipo);
			usuario.addContent(agencia);

			rootNode.addContent(usuario);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(document, new FileOutputStream(xmlFile));

		} catch (JDOMException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}

	public void crearXMLAgencia(String nombre1, String dir, String tel,
			String enc) {
		try {

			Document Usuario = new Document();
			Element concesionario = new Element("Concesionario");
			Usuario.setRootElement(concesionario);
			Element agencia = new Element("Agencia");

			Element nodos = new Element("Nodos");

			Element nombre = new Element("Nombre");
			Element direccion = new Element("Direccion");
			Element telefono = new Element("Telefono");
			Element encargado = new Element("Encargado");

			nombre.addContent(new Text(nombre1));
			direccion.addContent(new Text(dir));
			telefono.addContent(new Text(tel));
			encargado.addContent(new Text(enc));

			agencia.addContent(nombre);
			agencia.addContent(telefono);
			agencia.addContent(direccion);
			agencia.addContent(encargado);
			agencia.addContent(nodos);

			concesionario.addContent(agencia);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(Usuario, new FileOutputStream(new File("C:\\temp\\"
					+ nombre1 + ".xml")));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void guardarAgencias(String agencia, String marca, String anio,
			String mod, String col, String prec) {
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("C:\\temp\\" + agencia + ".xml");

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			Element Agencia = rootNode.getChild("Agencia");
			Element Nodos = Agencia.getChild("Nodos");

			Element Vehiculo = new Element("Vehiculo");
			Element Marca = new Element("Marca");
			Element Anio = new Element("Anio");
			Element Modelo = new Element("Modelo");
			Element Color = new Element("Color");
			Element Precio = new Element("Precio");

			Marca.addContent(new Text(marca));
			Anio.addContent(new Text(anio));
			Modelo.addContent(new Text(mod));
			Color.addContent(new Text(col));
			Precio.addContent(new Text(prec));

			Vehiculo.addContent(Marca);
			Vehiculo.addContent(Anio);
			Vehiculo.addContent(Modelo);
			Vehiculo.addContent(Color);
			Vehiculo.addContent(Precio);

			Nodos.addContent(Vehiculo);

			XMLOutputter wayout = new XMLOutputter(Format.getPrettyFormat());
			wayout.output(document, new FileOutputStream(xmlFile));

		} catch (JDOMException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}

}
