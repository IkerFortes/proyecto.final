import React from 'react';
import '../css/estilos.css'; // Importación del CSS separado
import { NavLink } from 'react-router-dom';

function Nav() {
  return (
    <nav className="navbar">
      <div className="nav-logo">MiTienda</div>
      <ul className="nav-links">
        <li>
          <NavLink to="/login" className="nav-item">Inicio</NavLink>
        </li>
        <li>
          <NavLink  to="/productos" end={true} className="nav-item">Productos</NavLink>
        </li>
        <li>
          <NavLink to="/productos/crear" className="nav-item">Crear producto</NavLink>
        </li>
        <li>
          <NavLink to="/compras" className="nav-item">Compras</NavLink>
        </li>
      </ul>
    </nav>
  );
}

export default Nav;