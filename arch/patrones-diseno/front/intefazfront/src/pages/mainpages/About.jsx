import { useState } from "react";
import axios from "axios";
import LoginForm from "../../componentes/LoginForm";
import DynamicForm from "../../componentes/DynamicForm";

function About() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [role, setRole] = useState("");

  const handleLoginSuccess = (userRole) => {
    setIsLoggedIn(true);
    setRole(userRole);
  };

  return (
    <div className="p-6 max-w-md mx-auto">
      {!isLoggedIn ? (
        <LoginForm onLoginSuccess={handleLoginSuccess} />
      ) : (
        <div>
          <h2 className="text-xl mb-4">Formulario para rol: {role}</h2>
          <DynamicForm role={role} />
        </div>
      )}
    </div>
  );
}

export default About;
