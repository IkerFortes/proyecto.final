import { con } from "../conexsions/conxion";
import "../css/estilos.css";
import InputField from "./InputField";

const Login = () => {
  // Manejador de login //
  const manejarLogin = async (e) => {
    e.preventDefault();

    // Datos del formulario
    const data = new FormData(e.target);

    // Genero el usuario que se verifica para el login
    const login = {
      email: data.get("email"),
      password: data.get("password"),
    };

    // Hago login
    try {
      const respuesta = await con.post("auth/login", login);
      alert("Login exitoso");

      const usuario = respuesta.data;

      // Guardo en session
      sessionStorage.setItem("usuario", {
        email: usuario.email,
        rol: usuario.rol,
      });

    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="login-container">
      <h2 className="form-title">Iniciar sesión</h2>
      <form
         method="post"
        className="login-form"
        onSubmit={manejarLogin}
      >
        <InputField
          name="email"
          type="email"
          placeholder="Correo electronico"
          icon="mail"
        />
        <InputField
          name="password"
          type="password"
          placeholder="Contaseña"
          icon="lock"
        />
        <button
          type="submit"
          className="login-button"
        >
          Iniciar sesión
        </button>
      </form>
    </div>
  );
};
export default Login;
