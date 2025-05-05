# Importamos la librería sys para acceder a los argumentos pasados al script
# Importamos la librería os para manejar rutas y directorios del sistema de archivos.
import sys
import os

def main():
    # Validación para checar que se hayan pasado exactamente dos argumentos (aparte del nombre del script).
    # Si no se cumple, despliega un mensaje y termina la ejecución.
    if len(sys.argv) != 3:
        print("Se necesitan 3 parámetros: python app.py <num1> <num2>")
        return

    try:
        # Los dos argumentos leídos se convierten a números flotantes.
        # Si los argumentos fueron numéricos, se realiza la suma y se guarda el resultado.
        num1 = float(sys.argv[1])
        num2 = float(sys.argv[2])
        resultado = num1 + num2
    except ValueError:
        # Si alguno de los dos argumentos leídos no es numérico, se despliega un error y termina la ejecución.
        print("Error: ambos argumentos deben ser números.")
        return

    print(f"La suma es: {resultado}")

    # Guardamos el resultado en el archivo resultados.txt dentro del volumen
    # El volumen se almacena en /data que se encuentra en las carpetas generadas al instalar Docker
    output_path = "/data/resultados.txt"
    os.makedirs(os.path.dirname(output_path), exist_ok=True)

    # Se abre el archivo en modo de agregado ("a", para no sobrescribir).
    # Escribe la operación y su resultado, como por ejemplo: 5.0 + 3.2 = 8.2.
    with open(output_path, "a") as f:
        f.write(f"{num1} + {num2} = {resultado}\n")

if __name__ == "__main__":
    main()