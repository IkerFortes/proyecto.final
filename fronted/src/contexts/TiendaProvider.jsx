import React, { useState } from 'react'
import { TiendaContext } from './TiendaContext'
import { con } from '../conexsions/conxion'

function TiendaProvider({children}) {
  const [productos, setProductos] = useState([])

  // Funcion para sacar todos los productos de la bbdd
  async function sacarProductos () {
    try {
      const respuesta = await con.get('/productos')
      setProductos(respuesta.data)
    } catch (error) {
      console.log(error)
    }
  }

  // Funcion para añadir un nuevo producto
  async function guardarProducto (nuevoProducto) {
    try {
      // El primer parámetro es el endpoint, el segundo es el objeto con los datos
      const respuesta = await con.post('/productos', nuevoProducto);

      console.log('Producto guardado:', respuesta.data);

      // Opcional: Refrescar la lista después de añadir
      sacarProductos();

    } catch (error) {
      console.error('Error al guardar producto:', error);
    }
  }

  // Sacar todos los usuarios
  async function sacarUsuarios() {
    try {
      const respuesta = await con.get("/usuarios");
      return respuesta.data;
    } catch (error) {
      console.error("Error al sacar usuarios:", error);
      return []; // Retorna un array vacío para evitar errores en el .map posterior
    }
  }

  // Sacar compras de un usuario específico
  async function sacarCompra(id) {
    try {
      const respuesta = await con.get(`pedidos/usuario/${id}`);
      return respuesta.data;
    } catch (error) {
      console.error(`Error al sacar compras del usuario ${id}:`, error);
      return [];
    }
  }

  // Funcion para sacar todas las compras de la tienda (Corregida)
  async function sacarCompras() {
    try {
      const usuarios = await sacarUsuarios();

      // Creamos un array de promesas
      const promesasDeCompras = usuarios.map(usuario => sacarCompra(usuario.id));

      // Esperamos a que todas se resuelvan en paralelo
      const resultados = await Promise.all(promesasDeCompras);

      // 'resultados' es un array de arrays, lo aplanamos con .flat()
      const todasLasCompras = resultados.flat();

      return todasLasCompras;
    } catch (error) {
      console.error("Error general en sacarCompras:", error);
    }
  }



  return (
    <TiendaContext.Provider value={{productos, sacarProductos, guardarProducto, sacarCompras}}>
      {children}
    </TiendaContext.Provider>
  )
}

export default TiendaProvider