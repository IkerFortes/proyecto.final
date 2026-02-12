import React, { useContext, useEffect, useState } from "react";
import { con } from "../../conexsions/conxion";
import { TiendaContext } from "../../contexts/TiendaContext";

function ProductosFormulario({ id }) {
  const { sacarProductos } = useContext(TiendaContext);
  // const navigate = useNavigate();

  // 1. Inicializamos con la estructura del producto
  const [producto, setProducto] = useState({
    nombre: "",
    precio: "",
    stock: "",
    conciertoid: "",
  });

  // 2. Al cambiar el ID, pedimos los datos y SOBREESCRIBIMOS el estado
  useEffect(() => {
    if (id) {
      async function cargarDatos() {
        try {
          const respuesta = await con.get(`productos/${id}`);
          setProducto(respuesta.data); // Esto cambia los valores en los inputs al instante
        } catch (error) {
          console.error("Error al cargar:", error);
        }
      }
      cargarDatos();
    } else {
      // Si el ID desaparece (modo crear), limpiamos los campos
      setProducto({
        nombre: "",
        precio: "",
        stock: "",
        conciertoid: "",
      });
    }
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProducto({
      ...producto, // Mantenemos lo que había
      [name]: value, // Cambiamos solo el campo que el usuario está tocando
    });
  };

  const manejarEnvio = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        await con.put(`productos/${id}`, producto);
        await sacarProductos();
        alert("Actualizado");
      } else {
        await con.post("productos", producto);
        alert("Creado");
      }
    } catch (error) {
      console.error(error);
    }
  };

  // Si es edición y aún no cargan los datos, mostramos cargando
  if (id && !producto) return <p>Cargando datos del producto...</p>;

  return (
    <>
      <div className="form-wrapper">
        <div className="form-container">
          <h3 className="form-title">
            {id ? "Editar producto" : "Registrar Nuevo Producto"}
          </h3>

          <form
            className="product-form"
            onSubmit={manejarEnvio}
          >
            <div className="form-grid">
              <div className="form-group">
                <label htmlFor="nombre">Nombre</label>
                <input
                  type="text"
                  name="nombre"
                  id="nombre"
                  placeholder="Ej. Camiseta Tour"
                  value={producto.nombre}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="precio">Precio</label>
                <input
                  type="number"
                  step="0.01"
                  name="precio"
                  id="precio"
                  placeholder="0.00"
                  value={producto.precio}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="stock">Stock</label>
                <input
                  type="number"
                  name="stock"
                  id="stock"
                  placeholder="Cant."
                  value={producto.stock}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="conciertoid">Concierto</label>
                <select
                  name="conciertoid"
                  id="concierto"
                  value={producto.conciertoid}
                  onChange={handleChange}
                  required
                >
                  <option
                    value=""
                    disabled
                  >
                    Selecciona evento
                  </option>
                  <option value="CONC-001">Gira Madrid 2024</option>
                  <option value="CONC-002">Festival Verano</option>
                  <option value="hola">Hola</option>
                </select>
              </div>
            </div>

            <button
              type="submit"
              className="add-btn"
            >
              {id ? "Confirmar cambios" : "Añadir Producto"}
            </button>
          </form>
        </div>
      </div>
    </>
  );
}

export default ProductosFormulario;
