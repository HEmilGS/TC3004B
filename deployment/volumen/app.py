import sys
from datetime import datetime

def guardar_historial(usuario, pregunta):
    with open("/data/historial.txt", "a") as f:
        f.write(f"[{datetime.now()}] {usuario}: {pregunta}\n")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Uso: python bot.py <usuario> <pregunta>")
        sys.exit(1)

    usuario = sys.argv[1]
    pregunta = sys.argv[2]

    guardar_historial(usuario, pregunta)
    print("Pregunta registrada correctamente.")
