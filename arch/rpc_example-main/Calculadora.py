from xmlrpc.server import SimpleXMLRPCServer #skeleton hecho por la librería 

# Clase que implementa las funciones remotas
class Calculadora:
    def suma(self, a, b):
        return a + b

# Crear el servidor XML-RPC en el puerto 8000
servidor = SimpleXMLRPCServer(("localhost", 8000))

# Registrar la clase Calculadora en el servidor
servidor.register_instance(Calculadora()) #registra los metodos 

print("Servidor RPC ejecutandose en el puerto 8000...")

# Ejecutar el servidor para recibir peticiones
servidor.serve_forever() #espera las llamadas
